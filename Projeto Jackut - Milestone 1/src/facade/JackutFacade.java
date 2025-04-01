package facade;

import services.UserService;
import java.util.*;

public class JackutFacade {
    private final UserService userService = new UserService();

    public void zerarSistema() {
        userService.zerarSistema();
    }

    public void criarUsuario(String login, String senha, String nome) {
        userService.criarUsuario(login, senha, nome);
    }

    public String abrirSessao(String login, String senha) {
        return userService.abrirSessao(login, senha);
    }

    public void editarPerfil(String idSessao, String atributo, String valor) {
        if (idSessao == null || idSessao.isEmpty()) {
            throw new RuntimeException("Usuário não cadastrado.");
        }
        userService.editarPerfil(idSessao, atributo, valor);
    }

    public String getAtributoUsuario(String login, String atributo) {
        return userService.getAtributoUsuario(login, atributo);
    }

    public String getAmigos(String login) {
        Set<String> amigos = userService.getAmigos(login);
        if (amigos.isEmpty()) {
            return "{}";
        }
        return "{" + String.join(",", amigos) + "}";
    }

    public void enviarRecado(String idSessao, String destinatario, String mensagem) {
        userService.enviarRecado(idSessao, destinatario, mensagem);
    }

    public String lerRecado(String idSessao) {
        return userService.lerRecado(idSessao);
    }

    public void adicionarAmigo(String idSessao, String amigo) {
        userService.adicionarAmigo(idSessao, amigo);
    }

    public boolean ehAmigo(String login, String amigo) {
        return userService.ehAmigo(login, amigo);
    }

    public void encerrarSistema() {
        userService.salvarDados();
    }
}