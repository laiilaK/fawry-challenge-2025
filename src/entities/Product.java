package entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    protected String name;
    protected int quantity;
    protected double price;
    protected Shippable shipDetails;
    protected Expirable expiryDetails;

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
}

