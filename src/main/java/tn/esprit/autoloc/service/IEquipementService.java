package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Equipement;

import java.util.List;
import java.util.Optional;

public interface IEquipementService {
    Equipement create(Equipement entity);

    List<Equipement> getAll();

    Optional<Equipement> getById(Long id);

    Equipement update(Long id, Equipement entity);

    void delete(Long id);
}
