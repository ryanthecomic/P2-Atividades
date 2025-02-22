package entities;

import java.util.ArrayList;
import java.util.Locale;

public class ReceitaFederal{

    private ArrayList<Pessoa> listaPessoas;

    public ReceitaFederal() {
        this.listaPessoas = new ArrayList<>();
    }

    public void addPessoa(Pessoa pessoa)
    {
        this.listaPessoas.add(pessoa);
    }

    public void getContents()
    {
        System.out.println("\nTAXES PAID:");
        for(Pessoa pessoa : listaPessoas)
        {

            System.out.println(pessoa.getNome()+": $ "+ String.format(Locale.US,"%.2f", pessoa.getTax(pessoa.getRenda())));

        }
    }

    public double getTax()
    {
        double tax = 0;
        for(Pessoa pessoa : listaPessoas)
        {
            tax += pessoa.getTax(pessoa.getRenda());
        }

         return tax;

    }

}
