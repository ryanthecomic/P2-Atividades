package entities;

public class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {return name;}

    public double getPrice() {return price;}

    @Override //Sobrecarga da aula 4
    public String toString() {
        return "Product[Name: " + name + ", Price: $" + price + "]";
    }
}
