package entities;

public class Refrigerator extends Product{

    private int size;

    public Refrigerator(String brand, double price, int size) {

        super(brand, price);
        this.size = size;

    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "- Refrigerator[Brand: " + getBrand() + ", Price: $" + getPrice() + ", Size: " + size + " meters]";
    }
}
