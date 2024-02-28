package inheritance;

import inheritance.entities.DemoBike;
import inheritance.entities.DemoCar;
import inheritance.entities.DemoVehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory mainFactory =
                Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = mainFactory.createEntityManager();

        entityManager.getTransaction().begin();

        DemoVehicle car = new DemoCar();
        DemoVehicle bike = new DemoBike();

        entityManager.persist(car);
        entityManager.persist(bike);

        entityManager.getTransaction().commit();
    }
}
