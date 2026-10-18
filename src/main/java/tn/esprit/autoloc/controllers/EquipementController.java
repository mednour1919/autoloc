package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

    private final IEquipementService service;

    public EquipementController(IEquipementService service) {
        this.service = service;
    }

    // CREATE : POST /api/equipements
    @PostMapping
    public ResponseEntity<Equipement> create(@RequestBody Equipement entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    // READ (liste) : GET /api/equipements
    @GetMapping
    public List<Equipement> getAll() {
        return service.getAll();
    }

    // READ (un élément) : GET /api/equipements/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE : PUT /api/equipements/{id}
    @PutMapping("/{id}")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement entity) {
        return service.update(id, entity);
    }

    // DELETE : DELETE /api/equipements/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
