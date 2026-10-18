package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Maintenance;

import java.util.List;
import java.util.Optional;

public interface IMaintenanceService {
    Maintenance create(Maintenance entity);

    List<Maintenance> getAll();

    Optional<Maintenance> getById(Long id);

    Maintenance update(Long id, Maintenance entity);

    void delete(Long id);
}
