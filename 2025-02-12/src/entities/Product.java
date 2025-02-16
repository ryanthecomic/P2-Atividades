package entities;

public class Product {

    private String brand;
    private double price;

    public Product(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {return brand;}

    public double getPrice() {return price;}

    @Override //Sobrecarga da aula 4
    public String toString() {
        return "- Product[Name: " + brand + ", Price: $" + price + "]";
    }
}
