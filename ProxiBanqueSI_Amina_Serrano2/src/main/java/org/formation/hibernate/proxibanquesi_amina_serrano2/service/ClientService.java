package org.formation.hibernate.proxibanquesi_amina_serrano2.service;

import org.formation.hibernate.proxibanquesi_amina_serrano2.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getClients();
    Client createClient(Client client);
    Optional<Client> getClient(Long id);
}