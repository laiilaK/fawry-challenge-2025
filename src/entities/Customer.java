package entities;


import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
public class Customer {
    protected String name;
    protected double balance;

}