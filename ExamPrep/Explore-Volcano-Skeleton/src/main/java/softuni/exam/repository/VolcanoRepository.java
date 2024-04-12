package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;

import java.util.List;
import java.util.Optional;
import java.util.Set;

// TODO:
@Repository
public interface VolcanoRepository extends JpaRepository<Volcano,Long> {


    Optional<Volcano> findByName(String name);

    //Set<Volcano> findAllByIsActiveIsTrueAndElevationGreaterThan(int elevation);
    @Query("SELECT v FROM Volcano v JOIN FETCH v.country WHERE v.elevation > 3000 AND v.isActive = true ORDER BY v.elevation DESC ")
    Set<Volcano> findAllActiveVolcanosAbove3000m();
}
