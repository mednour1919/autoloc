package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.repositories.PaiementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
 class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository repository;

    public PaiementServiceImpl(PaiementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Paiement create(Paiement entity) {
        return repository.save(entity);
    }

    @Override
    public List<Paiement> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Paiement> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Paiement update(Long id, Paiement entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Paiement introuvable avec l'identifiant : " + id);
        }
        entity.setIdPaiement(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Paiement introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}

