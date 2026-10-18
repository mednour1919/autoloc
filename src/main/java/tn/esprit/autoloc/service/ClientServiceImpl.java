package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client create(Client entity) {
        return repository.save(entity);
    }

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Client> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Client update(Long id, Client entity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Client introuvable avec l'identifiant : " + id);
        }
        // On force l'identifiant de l'entité reçue à correspondre à celui du chemin.
        // Adapte ce setter si ton attribut ID ou son setter porte un autre nom.
        entity.setIdClient(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Client introuvable avec l'identifiant : " + id);
        }
        repository.deleteById(id);
    }
}
