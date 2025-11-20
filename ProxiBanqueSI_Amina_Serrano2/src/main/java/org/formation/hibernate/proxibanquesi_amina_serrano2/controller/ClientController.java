package org.formation.hibernate.proxibanquesi_amina_serrano2.controller;

import lombok.RequiredArgsConstructor;
import org.formation.hibernate.proxibanquesi_amina_serrano2.model.Client;
import org.formation.hibernate.proxibanquesi_amina_serrano2.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService gestionnaireClients;

    @GetMapping("/clients")
    public List<Client> obtenirTousLesClients() {
        return gestionnaireClients.getClients();
    }

    @PostMapping("/clients")
    public Client ajouterNouveauClient(@RequestBody Client donneeClient) {
        return gestionnaireClients.createClient(donneeClient);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> rechercherClientParId(@PathVariable("id") Long identifiant) {
        Optional<Client> clientTrouve = gestionnaireClients.getClient(identifiant);

        return clientTrouve
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}