package tn.esprit.autoloc.repositories;

import tn.esprit.autoloc.entities.Employer;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployerRepository extends JpaRepository<Employer, Long>{
    Employer save(Employer entity);

    List<Employer> findAll();

    Optional<Employer> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);


}
