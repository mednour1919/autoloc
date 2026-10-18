package tn.esprit.autoloc.service;




import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final MaintenanceRepository repository;

    public MaintenanceServiceImpl(MaintenanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Maintenance create(Maintenance entity) {
        return repository.save(entity);
    }

    @Override
    public List<Maintenance> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Maintenance> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Maintenance update(Long id, Maintenance entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Maintenance introuvable avec l'identifiant : " + id);
        }
        // On force l'identifiant de l'entité reçue à correspondre à celui du chemin.
        // Adapte ce setter si ton attribut ID ou son setter porte un autre nom.
        entity.setIdMaintenance(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Maintenance introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}
