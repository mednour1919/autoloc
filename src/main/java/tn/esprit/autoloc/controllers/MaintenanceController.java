package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final IMaintenanceService service;

    public MaintenanceController(IMaintenanceService service) {
        this.service = service;
    }

    // CREATE : POST /api/maintenances
    @PostMapping
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    // READ (liste) : GET /api/maintenances
    @GetMapping
    public List<Maintenance> getAll() {
        return service.getAll();
    }

    // READ (un élément) : GET /api/maintenances/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE : PUT /api/maintenances/{id}
    @PutMapping("/{id}")
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance entity) {
        return service.update(id, entity);
    }

    // DELETE : DELETE /api/maintenances/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

