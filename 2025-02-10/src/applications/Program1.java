package applications;

import entities.Product;
import entities.ShoppingCart;

public class Program1{

    public static void main (String[] args){
        Product p1 = new Product("TV", 2000);
        Product p2 = new Product("Smartphone", 3000);

        ShoppingCart sc = new ShoppingCart(1);

        sc.addProduct(p1);
        sc.addProduct(p2);

        System.out.println(sc.getContents());
        System.out.println("Total: R$ " + sc.getTotalPrice() + "0");

        sc.removeProduct(p1);
        System.out.println(sc.getContents());
        System.out.println("Total: R$ " + sc.getTotalPrice() + "0");
    }
}
