package inheritance.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "demo_vehicles")
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="our_type")

public abstract class DemoVehicle {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Basic
    private String type;

    protected DemoVehicle(){}
    protected DemoVehicle(String type){
        this.type=type;
    }

}
