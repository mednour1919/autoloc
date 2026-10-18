package tn.esprit.autoloc.repositories;

import tn.esprit.autoloc.entities.employer;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployerRepository extends JpaRepository<employer, Long>{
    employer save(employer entity);

    List<employer> findAll();

    Optional<employer> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);


}
