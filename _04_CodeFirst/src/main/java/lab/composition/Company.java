package lab.composition;

import jakarta.persistence.*;
import lab.inheritance.Plane;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Basic
    private String name;
    @OneToMany(mappedBy = "owner")
    private List<Plane> planes;
    public Company() {
        this.planes = new ArrayList<>();
    }

    public Company(String name) {
        this();

        this.name = name;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }
}
