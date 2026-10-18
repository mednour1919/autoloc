package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Contrat;

import java.util.List;
import java.util.Optional;

public interface IContratService {
    Contrat create(Contrat entity);

    List<Contrat> getAll();

    Optional<Contrat> getById(Long id);

    Contrat update(Long id, Contrat entity);

    void delete(Long id);
}
