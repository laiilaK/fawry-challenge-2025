package entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import static java.lang.StrictMath.abs;

@Getter
@Setter
public class Product {
    protected String name;
    protected double price;
    protected int quantity;
    protected Shippable shipDetails;
    protected Expirable expiryDetails;

    private Product(String name, double price, int quantity, Shippable shipDetails, Expirable expiryDetails) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shipDetails = shipDetails;
        this.expiryDetails = expiryDetails;
    }

    public static Product createProduct(String name, double price, int quantity, Shippable shipDetails, Expirable expiryDetails){
        if (price < 0) {
            System.out.println("Price cannot be negative.");
            return null;
        }
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return null;
        }
        return new Product(name, price, quantity, shipDetails, expiryDetails);
    }

    public boolean isShippable(){
        if (shipDetails == null){
            return false;
        }
        return true;
    }

    public String getWeight(int n) {
        if (shipDetails == null) return "No weight details.";
        return String.valueOf(n * shipDetails.weight)  + shipDetails.measure  ;
    }

    public boolean hasExpired() {
        if (expiryDetails == null || expiryDetails.expiryDate.isAfter(LocalDate.now())){
            return false;
        }
        return true;
    }

    public void setQuantity(int num) {
        if (num < 0 && abs(num) > quantity ){
            System.out.println("Cannot change quantity to negative");
            return;
        }
        quantity += num;
    }
}

