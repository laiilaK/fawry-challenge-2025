package entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class Customer {
    protected String name;
    protected double balance;

    private Customer (String name, double balance){
        this.name = name;
        this.balance = balance;
    }

    public static Customer createCustomer(String name, double balance){
        if (balance < 0) {
            System.out.println("Cannot add negative balance");
            return null;
        }
        return new Customer(name, balance);
    }

    public void setBalance(double balance){
        if (balance < 0) {
            System.out.println("Cannot add negative balance");
        }
         this.balance = balance;
    }

    public void subtractBalance(double totalAmount) {
        balance-=totalAmount;
    }
}