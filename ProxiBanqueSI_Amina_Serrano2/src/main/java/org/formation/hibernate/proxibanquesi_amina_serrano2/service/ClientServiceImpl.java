package org.formation.hibernate.proxibanquesi_amina_serrano2.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.formation.hibernate.proxibanquesi_amina_serrano2.model.Client;
import org.formation.hibernate.proxibanquesi_amina_serrano2.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @PostConstruct
    private void initDb() {
        repository.saveAll(List.of(
                new Client("Serrano", "Amina", "0766231133"),
                new Client("Dating", "Bro", "0609876543")
        ));
    }

    @Override
    public List<Client> getClients() {
        return repository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return repository.save(client);
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return repository.findById(id);
    }
}