package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.employer;

import java.util.List;
import java.util.Optional;

public interface IEmployerService {
    employer create(employer entity);

    List<employer> getAll();

    Optional<employer> getById(Long id);

    employer update(Long id, employer entity);

    void delete(Long id);
}
