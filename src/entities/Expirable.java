package entities;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
public class Expirable {
    protected LocalDate expiryDate;
}
