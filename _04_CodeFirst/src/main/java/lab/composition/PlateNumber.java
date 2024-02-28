package lab.composition;

import jakarta.persistence.*;
import lab.inheritance.Car;

@Entity
@Table(name="plate_numbers")
public class PlateNumber {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String number;
    @OneToOne(mappedBy = "plateNumber")
    private Car car;
    public PlateNumber(){}

    public PlateNumber(String number){this.number=number;}
}
