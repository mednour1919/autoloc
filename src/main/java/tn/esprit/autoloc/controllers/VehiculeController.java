package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final IVehiculeService service;

    public VehiculeController(IVehiculeService service) {
        this.service = service;
    }

    // CREATE : POST /api/vehicules
    @PostMapping
    public ResponseEntity<Vehicule> create(@RequestBody Vehicule entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    // READ (liste) : GET /api/vehicules
    @GetMapping
    public List<Vehicule> getAll() {
        return service.getAll();
    }

    // READ (un élément) : GET /api/vehicules/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE : PUT /api/vehicules/{id}
    @PutMapping("/{id}")
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule entity) {
        return service.update(id, entity);
    }

    // DELETE : DELETE /api/vehicules/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

