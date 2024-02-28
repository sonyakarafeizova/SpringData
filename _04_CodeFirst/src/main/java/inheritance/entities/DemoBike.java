package inheritance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="bikes")
public class DemoBike extends DemoVehicle {

    private static final String BIKE_TYPE ="BIKE" ;

    public DemoBike() {
        super(BIKE_TYPE);
    }
}
