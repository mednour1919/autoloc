package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Paiement;

import java.util.List;
import java.util.Optional;

public interface IPaiementService {
    Paiement create(Paiement entity);

    List<Paiement> getAll();

    Optional<Paiement> getById(Long id);

    Paiement update(Long id, Paiement entity);

    void delete(Long id);
}
