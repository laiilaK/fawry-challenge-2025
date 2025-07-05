
import entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Product p = new Product("pen", 30 , 10, new Shippable(3, "g") , null);
        Product q = new Product("cheese", 32 , 90, null, new Expirable(LocalDate.now().plusDays(4)));
        Cart c = new Cart();
        Customer cust = new Customer("Tally" , 150);
        checkout(c , cust);
        c.addProduct(p,1);
        c.addProduct(p, 31);
        c.addProduct(p, 5);

        System.out.println("Checkout in manager");
        CheckoutManager ch = new CheckoutManager();
        ch.checkout(c , cust);

    }

    public static void checkout(Cart cart , Customer customer){
        if (cart.isEmpty()){
            System.out.println("There are no items in cart to purchase");
            return;
        }




        HashMap<Product , Integer> products = cart.getProducts();

        for (Map.Entry<Product , Integer> product : products.entrySet()){
            Product p  =  product.getKey() ;
            int num = product.getValue();
            if (checkExpire(p) ){
                System.out.println("Failed Transaction!\nThe item "+p.getName()+" has expired.\nRemove the item and try again.");
                return;
            }
            if ( checkQuantity(p) < num ){
                System.out.println("Failed Transaction!\nThe item "+p.getName()+" is out of stock.\nRemove the item and try again.");
                return;
            }
        }


        List<Product> shippedProducts = new ArrayList<>();

        for (Map.Entry<Product , Integer> product : products.entrySet()){
             Product p  =  product.getKey() ;
            if (p.isShippable()){
                shippedProducts.add(p);
            }
        }


        double subtotal = cart.getTotalPrice();
        double shipFees = 0;
        double totalAmount = subtotal + shipFees;


        if (!shippedProducts.isEmpty()) {
            ShippingService service = new ShippingService();
            service.shipItems(shippedProducts);
            shipFees = service.getFees();
            totalAmount += shipFees;

            if( totalAmount > customer.getBalance() ){
                System.out.println("There is not enough balance.");
                return;
            }

            System.out.println("*** Shipment notice ***");

            for (Product product : shippedProducts){
                int num = products.get(product);
                System.out.println(num + "x\t"+ product.getName()+"\t" + product.getWeight(num));

            }
        }


        if( totalAmount > customer.getBalance() ){
            System.out.println("There is not enough balance.");
            return;
        }


        System.out.println("*** Checkout receipt ***");

        for (Map.Entry<Product , Integer> product : products.entrySet()){
            Product p  =  product.getKey() ;
            int num = product.getValue();
            p.setQuantity(-num);
            System.out.println(num + "x\t"+ p.getName()+"\t" + p.getPrice() * num);

        }

        System.out.println("-----------------");

        System.out.println("Subtotal:" + subtotal);
        System.out.println("Shipping fees:" + shipFees);
        System.out.println("Amount:" + (totalAmount));
        System.out.println("-----------------");
        System.out.println("Purchase successfull!");

        customer.subtractBalance(totalAmount);
        System.out.println("Current balance: "+ customer.getBalance());



    }

    private static boolean checkExpire(Product p) {
        return p.hasExpired();
    }

    private static int checkQuantity(Product p) {
        return  p.getQuantity();
    }

}