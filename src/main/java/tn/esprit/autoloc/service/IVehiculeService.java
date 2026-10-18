package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {
    Vehicule create(Vehicule entity);

    List<Vehicule> getAll();

    Optional<Vehicule> getById(Long id);

    Vehicule update(Long id, Vehicule entity);

    void delete(Long id);
}
