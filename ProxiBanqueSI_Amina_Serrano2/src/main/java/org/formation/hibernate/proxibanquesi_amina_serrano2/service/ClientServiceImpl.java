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

    private final ClientRepository clientDao;

    @PostConstruct
    private void initDb() {
        Client premierClient = new Client("Serrano", "Amina", "0766231133");
        Client deuxiemeClient = new Client("Dating", "Bro", "0609876543");

        clientDao.saveAll(List.of(premierClient, deuxiemeClient));
    }

    @Override
    public List<Client> getClients() {
        return clientDao.findAll();
    }

    @Override
    public Client createClient(Client nouveauClient) {
        return clientDao.save(nouveauClient);
    }

    @Override
    public Optional<Client> getClient(Long identifiant) {
        return clientDao.findById(identifiant);
    }
}