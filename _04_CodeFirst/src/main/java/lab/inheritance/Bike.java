package lab.inheritance;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Bike extends Vehicle {

    private static final String BIKE_TYPE = "BIKE";

    public Bike() {
    }

    public Bike(String model, BigDecimal price, String fuelType) {
        super(BIKE_TYPE, model, price, fuelType);
    }

}