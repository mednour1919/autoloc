package tn.esprit.autoloc.service;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.repositories.AgenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository repository;

    public AgenceServiceImpl(AgenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Agence create(Agence entity) {
        return repository.save(entity);
    }

    @Override
    public List<Agence> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Agence> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Agence update(Long id, Agence entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Agence introuvable avec l'identifiant : " + id);
        }
        // On force l'identifiant de l'entité reçue à correspondre à celui du chemin.
        // Adapte ce setter si ton attribut ID ou son setter porte un autre nom.
        entity.setIdAgence(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Agence introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}

