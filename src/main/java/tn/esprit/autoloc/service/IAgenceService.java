package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Agence;

import java.util.List;
import java.util.Optional;

public interface IAgenceService {
    Agence create(Agence entity);

    List<Agence> getAll();

    Optional<Agence> getById(Long id);

    Agence update(Long id, Agence entity);

    void delete(Long id);
}
