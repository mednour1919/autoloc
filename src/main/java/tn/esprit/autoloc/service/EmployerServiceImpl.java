package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Employer;
import tn.esprit.autoloc.repositories.EmployerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements IEmployerService {

    private final EmployerRepository repository;

    public EmployerServiceImpl(EmployerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employer create(Employer entity) {
        return repository.save(entity);
    }

    @Override
    public List<Employer> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Employer> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Employer update(Long id, Employer entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Employer introuvable avec l'identifiant : " + id);
        }
        // On force l'identifiant de l'entité reçue à correspondre à celui du chemin.
        // Adapte ce setter si ton attribut ID ou son setter porte un autre nom.
        entity.setIdEmployer(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Employer introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}

