package entities;

public class TV extends Product{

    private int inches;

    public TV(String brand, double price, int inches) {

        super(brand, price); // Super Clase, vai pegar os métodos da entidade Product
        this.inches = inches;

    }

    public int getInches() {
        return inches;
    }

    @Override
    public String toString() {
        return "- TV[Brand: " + getBrand() + ", Price: $" + getPrice() + ", Size: " + inches + " inches]";
    }

}
