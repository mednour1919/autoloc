package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final IReservationService service;

    public ReservationController(IReservationService service) {
        this.service = service;
    }

    // CREATE : POST /api/reservations
    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    // READ (liste) : GET /api/reservations
    @GetMapping
    public List<Reservation> getAll() {
        return service.getAll();
    }

    // READ (un élément) : GET /api/reservations/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE : PUT /api/reservations/{id}
    @PutMapping("/{id}")
    public Reservation update(@PathVariable Long id, @RequestBody Reservation entity) {
        return service.update(id, entity);
    }

    // DELETE : DELETE /api/reservations/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
