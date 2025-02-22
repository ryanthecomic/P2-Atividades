package entities;

public class PessoaFisica extends Pessoa{
    private double gastosSaude;

    public PessoaFisica(String nome, double renda, double gastosSaude) {
        super(nome, renda);
        this.gastosSaude = gastosSaude;
    }

    public double getGastosSaude() {
        return gastosSaude;
    }

    public void setGastosSaude(double gastosSaude) {
        this.gastosSaude = gastosSaude;
    }


    @Override
    public double getTax(double renda) {
        double taxa = 0;
        if(renda < 20000)
        {
            if(this.gastosSaude > 0)
            {
                taxa = (renda * 0.15) - (gastosSaude * 0.5);
            }
            else
            {
                taxa = renda * 0.15;
            }

        }

        if(renda > 20000)
        {
            if(this.gastosSaude > 0)
            {
                taxa = (renda * 0.25) - (gastosSaude * 0.5);
            }
            else
            {
                taxa = renda * 0.25;
            }

        }

        return taxa;

    }

}
