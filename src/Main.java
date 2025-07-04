
import entities.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Product p = new Product("pen", 30 , 10, new Shippable(3, "g") , null);
        Product q = new Product("cheese", 32 , 90, null, new Expirable(LocalDate.now().plusDays(4)));
        Cart c = new Cart();
        checkout(c);
        c.addProduct(p,1);
        c.addProduct(p, 31);
        c.addProduct(p, 5);
        c.addProduct(q, 5);
        checkout(c);
    }

    public static void checkout(Cart cart){
        if (cart.isEmpty()){
            System.out.println("There are no items in cart to purchase");
            return;
        }

        if( cart.getTotalPrice() > 0 ){
            System.out.println("There is not enough balance.");
            return;
        }

        System.out.println("*** Shipment notice ***");

        HashMap<Product , Integer> products = cart.getProducts();

        for (Map.Entry<Product , Integer> product : products.entrySet()){
             Product p  =  product.getKey() ;
             int num = product.getValue();
            if (p.isShippable()){
                System.out.println(num + "x\t"+ p.getName()+"\t" + p.getWeight(num));
            }
        }

        System.out.println("*** Checkout receipt ***");

        for (Map.Entry<Product , Integer> product : products.entrySet()){
            Product p  =  product.getKey() ;
            int num = product.getValue();
            System.out.println(num + "x\t"+ p.getName()+"\t" + p.getPrice() * num);
        }

        System.out.println("-----------------");
        double subtotal = cart.getTotalPrice();
        double shipFees = 30;
        System.out.println("Subtotal:" + subtotal);
        System.out.println("Shipping fees:" + shipFees);
        System.out.println("Amount:" + (subtotal + shipFees));


    }

}