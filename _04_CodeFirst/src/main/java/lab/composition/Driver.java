package lab.composition;

import jakarta.persistence.*;
import lab.inheritance.Truck;

import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @ManyToMany(mappedBy = "drivers")
    private List<Truck> trucks;

    public Driver() {}
}