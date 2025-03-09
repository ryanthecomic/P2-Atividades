import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite uma frase:");

        try {
            String input = scanner.nextLine(); // Lê a frase inteira
            Scanner wordScanner = new Scanner(input);

            System.out.println("Palavras separadas:");
            while (wordScanner.hasNext()) {
                System.out.println(wordScanner.next());
            }

            wordScanner.close();
        } catch (NoSuchElementException e) {
            System.err.println("Erro: Faltam tokens na string!");
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
