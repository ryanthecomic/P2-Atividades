package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Profile implements Serializable {  // Adicione Serializable
    private static final long serialVersionUID = 1L;  // Versão de serialização

    private Map<String, String> attributes;

    public Profile() {
        this.attributes = new HashMap<>();
    }

    // todo: adiciona um atributo (não obrigatório)
    public void setAttribute(String key, String value) {
        attributes.put(key.toLowerCase(), value); // Padroniza como lowercase
    }

    // todo: retorna o valor ou "" se estiver vazio
    public String getAttribute(String key) {
        return attributes.getOrDefault(key.toLowerCase(), ""); // Retorna vazio se não existir
    }

    // todo retorna todos os atributos
    public Map<String, String> getAllAttributes() {
        return new HashMap<>(attributes); // Retorna cópia defensiva
    }
}