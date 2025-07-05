package entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter@Getter
public class Customer {
    protected String name;
    protected double balance;

    public void subtractBalance(double totalAmount) {
        balance-=totalAmount;
    }
}