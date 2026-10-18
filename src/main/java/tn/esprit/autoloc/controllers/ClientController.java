package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService service;

    public ClientController(IClientService service) {
        this.service = service;
    }

    // CREATE : POST /api/clients
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    // READ (liste) : GET /api/clients
    @GetMapping
    public List<Client> getAll() {
        return service.getAll();
    }

    // READ (un élément) : GET /api/clients/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE : PUT /api/clients/{id}
    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client entity) {
        return service.update(id, entity);
    }

    // DELETE : DELETE /api/clients/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
