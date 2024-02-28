package inheritance.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="cars")
@DiscriminatorValue("OurCar")
public class DemoCar extends DemoVehicle {
    private static final String CAR_TYPE = "CAR";

    public DemoCar() {
        super(CAR_TYPE);
    }
}
