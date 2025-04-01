package services;

import entities.*;
import exceptions.*;
import java.util.*;
import java.io.*;

public class UserService {

    private Map<String, User> usuarios;
    private Map<String, String> sessoesAtivas;

    //todo: Carrega dados ao iniciar
    public UserService() {
        Object loadedData = Jackut.load();
        if (loadedData instanceof Map) {
            this.usuarios = (Map<String, User>) loadedData;
            this.sessoesAtivas = new HashMap<>();
        } else {
            this.usuarios = new HashMap<>();
            this.sessoesAtivas = new HashMap<>();
        }
    }

    // US1.1 - Criação de conta
    // todo: criação do usuário
    // @param
    public void criarUsuario(String login, String senha, String nome) {
        if (login == null || login.isBlank()) {
            throw new RuntimeException("Login inválido.");
        }
        if (senha == null || senha.isBlank()) {
            throw new RuntimeException("Senha inválida.");
        }
        if (usuarios.containsKey(login)) {
            throw new UserAlreadyExistsException("Conta com esse nome já existe.");
        }

        usuarios.put(login, new User(login, senha, nome));
        salvarDados();
    }

    //todo: abre a sessão para o usuario
    // US1.1 - Autenticação
    public String abrirSessao(String login, String senha) {
        User usuario = usuarios.get(login);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Login ou senha inválidos.");
        }
        String idSessao = UUID.randomUUID().toString();
        sessoesAtivas.put(idSessao, login);
        return idSessao;
    }

    // US1.2 - Recuperação de dados
    public String getAtributoUsuario(String login, String atributo) {
        User usuario = usuarios.get(login);
        if (usuario == null) {
            throw new UserNotFoundException("Usuário não cadastrado.");
        }

        if ("nome".equalsIgnoreCase(atributo)) {
            return usuario.getNome();
        }

        String valor = usuario.getProfile().getAttribute(atributo);
        if (valor.isEmpty()) {
            throw new AttributeNotFilledException("Atributo não preenchido.");
        }
        return valor;
    }

    // US2.1 - Edição de perfil
    public void editarPerfil(String idSessao, String atributo, String valor) {
        User usuario = getUsuarioPorSessao(idSessao);
        usuario.getProfile().setAttribute(atributo, valor);
        salvarDados();
    }

    private User getUsuarioPorSessao(String idSessao) {
        String login = sessoesAtivas.get(idSessao);
        if (login == null) {
            throw new UserNotFoundException("Usuário não cadastrado."); // Mensagem exigida pelo teste
        }
        return usuarios.get(login);
    }

    public LinkedHashSet<String> getAmigos(String login) {
        User usuario = usuarios.get(login);
        if (usuario == null) {
            throw new UserNotFoundException("Usuário não cadastrado.");
        }
        return usuario.getAmigos(); // Retorna na ordem de confirmação
    }

    public void adicionarAmigo(String idSessao, String amigoLogin) {
        try {
            User usuario = getUsuarioPorSessao(idSessao);
            User amigo = usuarios.get(amigoLogin);

            if (amigo == null) throw new UserNotFoundException("Usuário não cadastrado.");
            if (usuario.getLogin().equals(amigoLogin))
                throw new FriendshipException("Usuário não pode adicionar a si mesmo como amigo.");

            if (usuario.isAmigo(amigoLogin)) {
                throw new FriendshipException("Usuário já está adicionado como amigo.");
            }

            if (amigo.getSolicitacoesPendentes().contains(usuario.getLogin())) {
                throw new FriendshipException("Usuário já está adicionado como amigo, esperando aceitação do convite.");
            }

            if (usuario.getSolicitacoesPendentes().contains(amigoLogin)) {
                usuario.confirmarAmizade(amigoLogin);
                amigo.confirmarAmizade(usuario.getLogin());
                usuario.getSolicitacoesPendentes().remove(amigoLogin);
            } else {
                amigo.adicionarSolicitacao(usuario.getLogin());
            }

            salvarDados();
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Usuário não cadastrado."); // Mensagem limpa para o teste
        } catch (FriendshipException e) {
            throw e;
        }
    }

    public boolean ehAmigo(String login1, String login2) {
        User user1 = usuarios.get(login1);
        User user2 = usuarios.get(login2);
        if (user1 == null || user2 == null) {
            throw new UserNotFoundException("Usuário não cadastrado.");
        }
        return user1.isAmigo(login2) && user2.isAmigo(login1);
    }

    /*private String validarSessao(String idSessao) {
        String login = sessoesAtivas.get(idSessao);
        if (login == null) throw new RuntimeException("Sessão inválida");
        return login;
    }*/

    public void enviarRecado(String idSessao, String destinatarioLogin, String mensagem) {
        User remetente = getUsuarioPorSessao(idSessao);
        User destinatario = usuarios.get(destinatarioLogin);

        if (destinatario == null) {
            throw new UserNotFoundException("Usuário não cadastrado.");
        }
        if (remetente.getLogin().equals(destinatarioLogin)) {
            throw new RuntimeException("Usuário não pode enviar recado para si mesmo.");
        }

        destinatario.adicionarRecado(new Message(remetente.getLogin(), mensagem));
        salvarDados();
    }

    public String lerRecado(String idSessao) {
        User usuario = getUsuarioPorSessao(idSessao);
        Message recado = usuario.lerRecado();

        if (recado == null) {
            throw new RuntimeException("Não há recados.");
        }

        return recado.toString(); // Retorna apenas o conteúdo do recado
    }

    public void salvarDados() {
        try {
            Jackut.save(usuarios);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar dados.");
        }
    }

    public void zerarSistema() {
        usuarios.clear();
        sessoesAtivas.clear();
        new File(Jackut.DATA_FILE).delete();
    }
}