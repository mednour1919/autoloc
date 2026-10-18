package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final IPaiementService service;

    public PaiementController(IPaiementService service) {
        this.service = service;
    }

    // CREATE : POST /api/paiements
    @PostMapping
    public ResponseEntity<Paiement> create(@RequestBody Paiement entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    // READ (liste) : GET /api/paiements
    @GetMapping
    public List<Paiement> getAll() {
        return service.getAll();
    }

    // READ (un élément) : GET /api/paiements/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE : PUT /api/paiements/{id}
    @PutMapping("/{id}")
    public Paiement update(@PathVariable Long id, @RequestBody Paiement entity) {
        return service.update(id, entity);
    }

    // DELETE : DELETE /api/paiements/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
