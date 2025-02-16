package applications;

import entities.Product;
import entities.ShoppingCart;
import entities.TV;
import entities.Stove;
import entities.Refrigerator;

public class Program1 {

    public static void main(String[] args) {

        TV tv = new TV("Nokia", 2000, 55);
        Stove stove = new Stove("Electrolux", 1500, 4);
        Refrigerator refrigerator = new Refrigerator("Philco", 3000, 2);

        ShoppingCart sc = new ShoppingCart(1);

        sc.addProduct(tv);
        sc.addProduct(stove);
        sc.addProduct(refrigerator);

        System.out.println("Adição dos produtos: TV, STOVE, REFRIGERATOR =>\n");
        System.out.println(sc.getContents());
        System.out.println("Total: R$ " + sc.getTotalPrice() + "0");

        sc.removeProduct(tv);
        System.out.println("\nRemoção da TV =>\n");
        System.out.println(sc.getContents());
        System.out.println("Total: R$ " + sc.getTotalPrice() + "0");
    }
}
