package entities;

public class PessoaJuridica extends Pessoa{
    private int numFuncionarios;

    public PessoaJuridica(String nome, double renda, int numFuncionarios) {
        super(nome, renda);
        this.numFuncionarios = numFuncionarios;
    }

    public int getNumFuncionarios() {
        return numFuncionarios;
    }

    public void setNumFuncionarios(int numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }

    @Override
    public double getTax(double renda) {

        double taxa = 0;
        if(numFuncionarios > 10)
        {
            taxa = renda * 0.14;
        }
        else
        {
            taxa = renda * 0.16;
        }

        return taxa;

    }

}
