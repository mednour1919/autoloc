package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    Reservation create(Reservation entity);

    List<Reservation> getAll();

    Optional<Reservation> getById(Long id);

    Reservation update(Long id, Reservation entity);

    void delete(Long id);
}
