package tn.esprit.autoloc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
}