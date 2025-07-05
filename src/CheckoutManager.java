import entities.Cart;
import entities.Customer;
import entities.Product;
import entities.ShippingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutManager {



    public static void checkout(Cart cart , Customer customer){
        if (cart.isEmpty()){
            System.out.println("There are no items in cart to purchase");
            return;
        }


        HashMap<Product, Integer> products = cart.getProducts();

        if (checkProductExpiryandQuantity(products)) return;

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

        if (balanceNotSufficient(totalAmount , customer.getBalance())) return;


        if (!shippedProducts.isEmpty()) {
            ShippingService service = new ShippingService();
            service.shipItems(shippedProducts);
            shipFees = service.getFees();
            totalAmount += shipFees;

            if (balanceNotSufficient(totalAmount , customer.getBalance())) return;

            System.out.println("*** Shipment notice ***");

            for (Product product : shippedProducts){
                int num = products.get(product);
                System.out.println(num + "x\t"+ product.getName()+"\t" + product.getWeight(num));

            }
        }

        printCheckoutReceipt(products);


        System.out.println("-----------------");

        System.out.println("Subtotal:" + subtotal);
        System.out.println("Shipping fees:" + shipFees);
        System.out.println("Amount:" + (totalAmount));
        System.out.println("-----------------");
        System.out.println("Purchase successfull!");

        customer.subtractBalance(totalAmount);
        System.out.println("Current balance: "+ customer.getBalance());



    }


    private static void printCheckoutReceipt(HashMap<Product, Integer> products) {
        System.out.println("*** Checkout receipt ***");

        for (Map.Entry<Product , Integer> product : products.entrySet()){
            Product p  =  product.getKey() ;
            int num = product.getValue();
            p.setQuantity(-num);
            System.out.println(num + "x\t"+ p.getName()+"\t" + p.getPrice() * num);

        }

    }

    private static boolean balanceNotSufficient(double amount , double balance){
        if( amount > balance ){
            System.out.println("There is not enough balance.");
            return true;
        }
        return false;
    }

    private static boolean checkProductExpiryandQuantity(HashMap<Product, Integer> products) {
        for (Map.Entry<Product , Integer> product : products.entrySet()){
            Product p  =  product.getKey() ;
            int num = product.getValue();
            if (checkExpire(p) ){
                System.out.println("Failed Transaction!\nThe item "+p.getName()+" has expired.\nRemove the item and try again.");
                return true;
            }
            if ( checkQuantity(p) < num ){
                System.out.println("Failed Transaction!\nThe item "+p.getName()+" is out of stock.\nRemove the item and try again.");
                return true;
            }
        }

        return false;

    }

    private static boolean checkExpire(Product p) {
        return p.hasExpired();
    }

    private static int checkQuantity(Product p) {
        return  p.getQuantity();
    }


}
