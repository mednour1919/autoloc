package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entities.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    Client create(Client entity);

    List<Client> getAll();

    Optional<Client> getById(Long id);

    Client update(Long id, Client entity);

    void delete(Long id);
}
