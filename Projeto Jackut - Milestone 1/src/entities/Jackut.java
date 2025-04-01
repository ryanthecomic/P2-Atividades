package entities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Jackut {
    public static final String DATA_FILE = "jackut_data.ser";

    //todo: função para o jackut salvar seus dados
    public static void save(Map<String, User> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            // Salva uma cópia defensiva
            oos.writeObject(new HashMap<>(usuarios));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar dados", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, User> load() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            return (Map<String, User>) ois.readObject();
        } catch (Exception e) {
            // Se corrompido, renomeia o arquivo e começa novo
            file.renameTo(new File(DATA_FILE + ".backup_" + System.currentTimeMillis()));
            return null;
        }
    }
}