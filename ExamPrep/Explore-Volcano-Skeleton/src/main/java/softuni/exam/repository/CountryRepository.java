package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Country;

import java.util.Optional;
import java.util.Set;

// TODO:
@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {


    Optional<Country> findByName(String name);

Optional<Country> getCountryById(Long country);
}
