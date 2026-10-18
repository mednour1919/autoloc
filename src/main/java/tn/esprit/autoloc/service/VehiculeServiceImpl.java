package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository repository;

    public VehiculeServiceImpl(VehiculeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehicule create(Vehicule entity) {
        return repository.save(entity);
    }

    @Override
    public List<Vehicule> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Vehicule> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Vehicule update(Long id, Vehicule entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Vehicule introuvable avec l'identifiant : " + id);
        }
        // On force l'identifiant de l'entité reçue à correspondre à celui du chemin.
        // Adapte ce setter si ton attribut ID ou son setter porte un autre nom.
        entity.setIdVehicule(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Vehicule introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}

