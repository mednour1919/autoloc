package tn.esprit.autoloc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.EquipementRepository;
import tn.esprit.autoloc.repositories.VehiculeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculeServiceImpl implements IVehiculeService {

    private static final DateTimeFormatter FORMAT_DATE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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

    // Toutes les 60 secondes, en commençant 5 secondes après le démarrage
    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void actualiserEtatVehicules() {
        List<Vehicule> vehicules = repository.findAll();
        String date = LocalDateTime.now().format(FORMAT_DATE);

        System.out.println("==================================");
        System.out.println("ACTUALISATION DES VEHICULES");
        System.out.println("Nombre total de véhicules : " + vehicules.size());
        System.out.println("Date et heure : " + date);
        System.out.println("==================================");
    }

    // Chaque lundi à 9h00, heure de Tunis
    @Scheduled(cron = "0 0 9 ? * MON", zone = "Africa/Tunis")
    public void genererRapportVehicules() {
        List<Vehicule> vehicules = repository.findAll();
        String date = LocalDateTime.now().format(FORMAT_DATE);

        System.out.println("==================================");
        System.out.println("RAPPORT HEBDOMADAIRE DES VEHICULES");
        System.out.println("Nombre total de véhicules : " + vehicules.size());
        System.out.println("Date et heure de génération : " + date);
        System.out.println("==================================");
    }
}