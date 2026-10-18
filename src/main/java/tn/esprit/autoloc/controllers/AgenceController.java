package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final IAgenceService service;

    public AgenceController(IAgenceService service) {
        this.service = service;
    }

    // CREATE : POST /api/agences
    @PostMapping
    public ResponseEntity<Agence> create(@RequestBody Agence entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    // READ (liste) : GET /api/agences
    @GetMapping
    public List<Agence> getAll() {
        return service.getAll();
    }

    // READ (un élément) : GET /api/agences/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Agence> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE : PUT /api/agences/{id}
    @PutMapping("/{id}")
    public Agence update(@PathVariable Long id, @RequestBody Agence entity) {
        return service.update(id, entity);
    }

    // DELETE : DELETE /api/agences/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
