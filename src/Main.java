
import entities.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args){
        Product p = Product.createProduct("pen", 10 , 10, new Shippable(3, "g") , null);
        Product q = Product.createProduct("cheese", 5 , 90, null, new Expirable(LocalDate.now().plusDays(4)));
        Cart c = new Cart();
        Customer cust = new Customer("Tally" , 1111);
        c.addProduct(p,1);
        c.addProduct(p, 31);
        c.addProduct(q, 5);

        System.out.println("Checkout in manager");
        CheckoutManager ch = new CheckoutManager();
        ch.checkout(c , cust);

    }
}