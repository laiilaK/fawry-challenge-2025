package entities;

import lombok.Getter;

import java.util.List;

public class ShippingService {
    @Getter
    double fees = 50;
    public void shipItems (List<Product> productList){
        // logic of shipping items
    }
}
