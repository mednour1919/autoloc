package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.repositories.EquipementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementServiceImpl implements IEquipementService {

    private final EquipementRepository repository;

    public EquipementServiceImpl(EquipementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Equipement create(Equipement entity) {
        return repository.save(entity);
    }

    @Override
    public List<Equipement> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Equipement> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Equipement update(Long id, Equipement entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Equipement introuvable avec l'identifiant : " + id);
        }
        // On force l'identifiant de l'entité reçue à correspondre à celui du chemin.
        // Adapte ce setter si ton attribut ID ou son setter porte un autre nom.
        entity.setIdEquipement(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Equipement introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}

