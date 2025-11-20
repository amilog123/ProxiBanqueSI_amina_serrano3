package org.formation.hibernate.proxibanquesi_amina_serrano2.controller;

import lombok.RequiredArgsConstructor;
import org.formation.hibernate.proxibanquesi_amina_serrano2.model.Client;
import org.formation.hibernate.proxibanquesi_amina_serrano2.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping("/clients")
    public List<Client> getClients() {
        return service.getClients();
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client) {
        return service.createClient(client);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return service.getClient(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}