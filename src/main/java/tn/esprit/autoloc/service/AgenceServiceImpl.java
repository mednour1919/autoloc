package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.AgenceRepository;
import tn.esprit.autoloc.repositories.VehiculeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository repository;
    private final VehiculeRepository vehiculeRepository;

    public AgenceServiceImpl(AgenceRepository repository,
                             VehiculeRepository vehiculeRepository) {
        this.repository = repository;
        this.vehiculeRepository = vehiculeRepository;
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

    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        // On met de côté les véhicules reçus, puis on enregistre l'agence seule
        List<Vehicule> vehiculesRecus =
                agence.getVehicules() != null ? agence.getVehicules() : new ArrayList<>();
        agence.setVehicules(new ArrayList<>());
        Agence nouvelleAgence = repository.save(agence);

        for (Vehicule recu : vehiculesRecus) {
            Vehicule vehicule = recu;

            // Véhicule existant : on le recharge pour ne pas écraser ses champs
            if (recu.getIdVehicule() != null) {
                vehicule = vehiculeRepository.findById(recu.getIdVehicule())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Vehicule introuvable avec l'identifiant : " + recu.getIdVehicule()));
            }

            vehicule.setAgence(nouvelleAgence);
            nouvelleAgence.getVehicules().add(vehiculeRepository.save(vehicule));
        }

        return nouvelleAgence;
    }
}