package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.repositories.ContratRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
class ContratServiceImpl implements IContratService {

    private final ContratRepository repository;

    public ContratServiceImpl(ContratRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contrat create(Contrat entity) {
        return repository.save(entity);
    }

    @Override
    public List<Contrat> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Contrat> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Contrat update(Long id, Contrat entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Contrat introuvable avec l'identifiant : " + id);
        }
        // On force l'identifiant de l'entité reçue à correspondre à celui du chemin.
        // Adapte ce setter si ton attribut ID ou son setter porte un autre nom.
        entity.setIdContrat(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Contrat introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}

