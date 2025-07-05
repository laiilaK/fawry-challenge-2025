package entities;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class Cart {
    protected HashMap<Product, Integer> products;
    protected double totalPrice;
    public Cart(){
        products = new HashMap<>();
        totalPrice= 0;
    }

    public void addProduct(Product p, int q){
        int num = 0;
        if (products.get(p) != null) {
            num = products.get(p);
            q+=num;
        }
        if (q > p.getQuantity()){
            System.out.println("Cannot add this quantity");
            return;
        }
        if (p.hasExpired()){
            System.out.println("This product has expired");
            return;
        }
        products.put(p, q);
        q -=num;
        totalPrice += q*p.getPrice();
    }

    private void removeProduct(Product p){
        int num = products.get(p);
        totalPrice -= num*p.getPrice();
        products.remove(p);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
