package entities;

import java.io.Serializable;
import java.util.LinkedHashSet; // Mapa de strings linkados, para amigos adicionados na ordem correta
import java.util.Queue; // Fila de recados
import java.util.Set; // Mapa de strings para solicitações
import java.util.LinkedList; // Para lista de recados

/**
 * Esta entidade representa um usuário do sistema Jackut, contendo todas as informações
 * e funcionalidades relacionadas ao perfil, amizades e mensagens.
 *
 * <p>A classe é serializável para permitir persistência dos dados.</p>
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String login;
    private final String senha;
    private final String nome;
    private Profile profile;

    /** Conjunto de amigos do usuário, mantendo a ordem de confirmação */
    private final LinkedHashSet<String> amigos = new LinkedHashSet<>();

    /** Solicitações de amizade pendentes */
    private final LinkedHashSet<String> solicitacoesPendentes = new LinkedHashSet<>();

    /** Fila de mensagens recebidas (estrutura FIFO) */
    private final Queue<Message> recados = new LinkedList<>();

    /**
     * Constrói um novo usuário com os dados básicos.
     *
     * @param login Identificador único do usuário
     * @param senha Senha de acesso
     * @param nome Nome de exibição do usuário
     */
    public User(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.profile = new Profile();
    }

    // Getters básicos

    /**
     * @return O login do usuário
     */
    public String getLogin() { return login; }

    /**
     * @return A senha do usuário
     */
    public String getSenha() { return senha; }

    /**
     * @return O nome de exibição do usuário
     */
    public String getNome() { return nome; }

    /**
     * @return O perfil do usuário
     */
    public Profile getProfile() { return profile; }

    // Métodos para gerenciar amizades

    /**
     * Adiciona uma nova solicitação de amizade.
     *
     * @param loginAmigo Login do usuário que solicitou amizade
     */
    public void adicionarSolicitacao(String loginAmigo) {
        solicitacoesPendentes.add(loginAmigo);
    }

    /**
     * Confirma uma amizade, adicionando o usuário à lista de amigos.
     *
     * @param loginAmigo Login do usuário a ser adicionado como amigo
     */
    public void confirmarAmizade(String loginAmigo) {
        solicitacoesPendentes.remove(loginAmigo);
        amigos.add(loginAmigo); // LinkedHashSet mantém a ordem de inserção
    }

    /**
     * Verifica se um usuário é amigo.
     *
     * @param loginAmigo Login do usuário a verificar
     * @return true se for amigo, false caso contrário
     */
    public boolean isAmigo(String loginAmigo) {
        return amigos.contains(loginAmigo);
    }

    /**
     * Retorna a lista de amigos na ordem de confirmação.
     *
     * @return Cópia defensiva do conjunto de amigos
     */
    public LinkedHashSet<String> getAmigos() {
        return new LinkedHashSet<>(amigos);
    }

    // Métodos para gerenciar recados

    /**
     * Adiciona um novo recado à fila do usuário.
     *
     * @param recado Mensagem a ser adicionada
     */
    public void adicionarRecado(Message recado) {
        recados.add(recado);
    }

    /**
     * Lê e remove o próximo recado da fila (FIFO).
     *
     * @return O próximo recado ou null se não houver
     */
    public Message lerRecado() {
        return recados.poll();
    }

    /**
     * Verifica se existem recados não lidos.
     *
     * @return true se houver recados, false caso contrário
     */
    public boolean temRecados() {
        return !recados.isEmpty();
    }

    /**
     * Retorna as solicitações de amizade pendentes.
     *
     * @return Cópia defensiva do conjunto de solicitações
     */
    public Set<String> getSolicitacoesPendentes() {
        return new LinkedHashSet<>(solicitacoesPendentes);
    }
}