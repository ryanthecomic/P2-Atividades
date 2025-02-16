package entities;

import java.util.ArrayList;

public class ShoppingCart {

    private int customerID;
    private ArrayList<Product> productList;

    public ShoppingCart(int customerID) {
        this.customerID = customerID;
        this.productList = new ArrayList<Product>();
    }

    public void addProduct(Product product) { // Não é limitado por regras relacionais (BD)
        this.productList.add(product);
    }

    public void removeProduct(Product product) {
        this.productList.remove(product);
    }

    public double getTotalPrice() {

        double totalPrice = 0.0;
        for (Product product : this.productList) {

            totalPrice += product.getPrice();

        }
        return totalPrice;
    }

    public String getContents() {
        StringBuilder contents = new StringBuilder("Shopping Cart Contents:\n");
        for (Product product : this.productList) {
            contents.append(product).append("\n"); // Assuming Product.toString() is implemented
        }
        return contents.toString();
    }

    public int getCustomerID() {return this.customerID;}

    public int getItemCount() {return this.productList.size();}

}
