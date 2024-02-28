package lab.inheritance;

import jakarta.persistence.*;

@MappedSuperclass
public class IdType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;


    @Basic
    protected String type;
}
