import entities.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MainTest {
    @Test
    void test1(){
        // check if adding the same product multiple times won't result in duplicates or negative quantity
        Product cheese = Product.createProduct("cheese", 10.7 , 12, null, new Expirable(LocalDate.now().plusDays(4)));
        Cart c = new Cart();
        Customer cust = Customer.createCustomer("Tally" , 1111);
        c.addProduct(cheese,5);
        c.addProduct(cheese,5);
        c.addProduct(cheese,5);
        c.addProduct(cheese,1);
        CheckoutManager ch = new CheckoutManager();
        ch.checkout(c , cust);

    }

    @Test
    void test2(){
        // check if adding all products not shippable will not result in shipping fee
        Product cheese = Product.createProduct("cheese", 5 , 90, null, new Expirable(LocalDate.now().plusDays(4)));
        Cart c = new Cart();
        Customer cust = Customer.createCustomer("Tally" , 1111);
        c.addProduct(cheese,5);
        CheckoutManager ch = new CheckoutManager();
        ch.checkout(c , cust);

    }

    @Test
    void test3(){
        // check if adding all products not shippable will not result in shipping fee
        Product chocolate = Product.createProduct("chocolate", 7 , 90, new Shippable(90 , "g"), new Expirable(LocalDate.now().plusDays(4)));
        Cart c = new Cart();
        Customer cust = Customer.createCustomer("Tally" , 1111);
        c.addProduct(chocolate,5);
        CheckoutManager ch = new CheckoutManager();
        ch.checkout(c , cust);

    }
}
