package lab.inheritance;

import jakarta.persistence.*;
import lab.composition.Driver;

import java.math.BigDecimal;
import java.util.List;

@Entity

public class Truck extends Vehicle {
    private static final String TRUCK_TYPE = "TRUCK";
    @Column(name = "load_capacity")
    private double loadCapacity;

    @ManyToMany
    @JoinTable(
            name = "trucks_drivers",
            joinColumns = @JoinColumn(name = "truck_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id")
    )
    private List<Driver> drivers;

    public Truck() {}

    public Truck(String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(TRUCK_TYPE, model, price, fuelType);

        this.loadCapacity = loadCapacity;
    }
}
