package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.EquipementRepository;
import tn.esprit.autoloc.repositories.VehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository repository;
    private final EquipementRepository equipementRepository;

    public VehiculeServiceImpl(VehiculeRepository repository,
                               EquipementRepository equipementRepository) {
        this.repository = repository;
        this.equipementRepository = equipementRepository;
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

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = repository.findById(idVehicule)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Vehicule introuvable avec l'identifiant : " + idVehicule));

        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Equipement introuvable avec l'identifiant : " + idEquipement));

        if (!vehicule.getEquipements().contains(equipement)) {
            vehicule.getEquipements().add(equipement);
        }
        return repository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = repository.findById(idVehicule)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Vehicule introuvable avec l'identifiant : " + idVehicule));

        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Equipement introuvable avec l'identifiant : " + idEquipement));

        vehicule.getEquipements().remove(equipement);
        return repository.save(vehicule);
    }
}