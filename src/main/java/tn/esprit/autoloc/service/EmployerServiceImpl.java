package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.entities.Employer;
import tn.esprit.autoloc.repositories.AgenceRepository;
import tn.esprit.autoloc.repositories.EmployerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements IEmployerService {

    private final EmployerRepository repository;
    private final AgenceRepository agenceRepository;

    public EmployerServiceImpl(EmployerRepository repository,
                               AgenceRepository agenceRepository) {
        this.repository = repository;
        this.agenceRepository = agenceRepository;
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

    @Override
    @Transactional
    public Employer desaffecterEmployer(Long idEmployer) {
        Employer employer = repository.findById(idEmployer)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employer introuvable avec l'identifiant : " + idEmployer));

        employer.setAgence(null);
        return repository.save(employer);
    }

    @Override
    @Transactional
    public Employer affecterEmployer(Long idEmployer, Long idAgence) {
        Employer employer = repository.findById(idEmployer)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employer introuvable avec l'identifiant : " + idEmployer));

        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Agence introuvable avec l'identifiant : " + idAgence));

        employer.setAgence(agence);
        return repository.save(employer);
    }
}