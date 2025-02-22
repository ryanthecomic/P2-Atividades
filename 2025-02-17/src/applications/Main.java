package applications;

import java.util.Scanner;
import java.util.Locale;
import entities.ReceitaFederal;
import entities.PessoaFisica;
import entities.PessoaJuridica;

public class Main {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        String name;
        double renda, gastoSaude;
        int n, empregados;
        String tipo;

        ReceitaFederal receita = new ReceitaFederal(); // faz o L

        System.out.print("Enter the number of tax payers: ");
        n = scan.nextInt();
        scan.nextLine();

        for(int i = 1; i <= n; i++){
            System.out.println("Tax payer "+"#"+i+" data:");

            System.out.print("Individual or company (i/c)? ");
            tipo = scan.nextLine();

            if(tipo.equals("i"))
            {
                System.out.print("Name:");
                name = scan.nextLine();

                System.out.print("Anual income: ");
                renda = scan.nextDouble();

                System.out.print("Health expenditures: ");
                gastoSaude = scan.nextDouble();
                scan.nextLine();

                PessoaFisica pessoa = new PessoaFisica(name, renda, gastoSaude);


                receita.addPessoa(pessoa);

            }
            else if(tipo.equals("c"))
            {
                System.out.print("Name:");
                name = scan.nextLine();

                System.out.print("Anual income: ");
                renda = scan.nextDouble();

                System.out.print("Number of employes: ");
                empregados = scan.nextInt();
                scan.nextLine();

                PessoaJuridica pessoa = new PessoaJuridica(name, renda, empregados);

                receita.addPessoa(pessoa);

            }

        }
        receita.getContents();
        System.out.println();
        System.out.println("TOTAL TAXES: $" + String.format(Locale.US, "%.2f", receita.getTax()));
        scan.close();
    }

}
