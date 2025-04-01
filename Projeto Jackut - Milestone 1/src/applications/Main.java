package applications;

import easyaccept.EasyAccept;
import facade.JackutFacade;
import services.*;
import entities.*;
import exceptions.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JackutFacade facade = new JackutFacade(); // Instância da fachada
        int opcao;

        while(true){

        System.out.println("=== MENU EASYACCEPT JACKUT ===");
        System.out.println("1 - Executar testes do EasyAccept");
        System.out.println("2 - Executar teste específico do EasyAccept");
        System.out.println("3 - Sair");
        System.out.print("Escolha uma opção: ");

        opcao = sc.nextInt();

        sc.nextLine();

        switch (opcao) {
            case 2:
                System.out.println("Qual teste deseja executar?");
                int opcao2 = sc.nextInt();
                sc.nextLine();

                executarTestesEspecificoEasyAccept(opcao2);

                break;
            case 1:
                executarTestesEasyAccept();
                break;
            case 3:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            case 4:
                UserService service = new UserService();
                service.criarUsuario("jpsauve", "senha", "Jacques");
                service.criarUsuario("oabath", "senha", "Osorio");

                String id1 = service.abrirSessao("jpsauve", "senha");
                service.adicionarAmigo(id1, "oabath"); // Envia solicitação

                String id2 = service.abrirSessao("oabath", "senha");
                service.adicionarAmigo(id2, "jpsauve"); // Confirma amizade

                System.out.println(service.ehAmigo("jpsauve", "oabath")); // Deve imprimir true
                System.out.println(service.getAmigos("jpsauve")); // Deve imprimir {oabath}
            default:
                System.out.println("Opção inválida!");
            }
        }
    }
    // todo: Método para executar todos os testes do EasyAccept
    private static void executarTestesEasyAccept() {
        String[][] argsTestes = {
                {"facade.JackutFacade", "src/scripts/us1_1.txt"},
                {"facade.JackutFacade", "src/scripts/us1_2.txt"},
                {"facade.JackutFacade", "src/scripts/us2_1.txt"},
                {"facade.JackutFacade", "src/scripts/us2_2.txt"},
                {"facade.JackutFacade", "src/scripts/us3_1.txt"},
                {"facade.JackutFacade", "src/scripts/us3_2.txt"},
                {"facade.JackutFacade", "src/scripts/us4_1.txt"},
                {"facade.JackutFacade", "src/scripts/us4_2.txt"}
        };

        for (String[] args : argsTestes) {
            EasyAccept.main(args);
        }
    }

    // todo: Método para executar um teste específico do EasyAccept
    private static void executarTestesEspecificoEasyAccept(int x) {
        String[][] argsTestes = {
                {"facade.JackutFacade", "src/scripts/us1_1.txt"},
                {"facade.JackutFacade", "src/scripts/us1_2.txt"},
                {"facade.JackutFacade", "src/scripts/us2_1.txt"},
                {"facade.JackutFacade", "src/scripts/us2_2.txt"},
                {"facade.JackutFacade", "src/scripts/us3_1.txt"},
                {"facade.JackutFacade", "src/scripts/us3_2.txt"},
                {"facade.JackutFacade", "src/scripts/us4_1.txt"},
                {"facade.JackutFacade", "src/scripts/us4_2.txt"}
        };

            EasyAccept.main(argsTestes[x-1]);

    }

}