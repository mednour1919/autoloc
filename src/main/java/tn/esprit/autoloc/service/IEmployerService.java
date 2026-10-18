package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Employer;

import java.util.List;
import java.util.Optional;

public interface IEmployerService {
    Employer create(Employer entity);

    List<Employer> getAll();

    Optional<Employer> getById(Long id);

    Employer update(Long id, Employer entity);

    void delete(Long id);
}
