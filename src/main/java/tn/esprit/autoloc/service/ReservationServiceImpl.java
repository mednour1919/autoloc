package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reservation create(Reservation entity) {
        return repository.save(entity);
    }

    @Override
    public List<Reservation> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Reservation> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Reservation update(Long id, Reservation entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Reservation introuvable avec l'identifiant : " + id);
        }
        // On force l'identifiant de l'entité reçue à correspondre à celui du chemin.
        // Adapte ce setter si ton attribut ID ou son setter porte un autre nom.
        entity.setIdReservation(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Reservation introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}
