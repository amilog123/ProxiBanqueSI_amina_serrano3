package org.formation.hibernate.proxibanquesi_amina_serrano2.controller;

import lombok.RequiredArgsConstructor;
import org.formation.hibernate.proxibanquesi_amina_serrano2.model.CompteBancaire;
import org.formation.hibernate.proxibanquesi_amina_serrano2.service.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CompteController {

    private final CompteService gestionnaireComptes;

    @GetMapping("/comptes")
    public List<CompteBancaire> listerTousLesComptes() {
        return gestionnaireComptes.getComptes();
    }

    @GetMapping("/clients/{clientId}/comptes")
    public List<CompteBancaire> recupererComptesClient(@PathVariable("clientId") Long idClient) {
        return gestionnaireComptes.getComptesByClientId(idClient);
    }

    @GetMapping("/comptes/{id}")
    public ResponseEntity<CompteBancaire> consulterCompte(@PathVariable("id") Long numeroIdentifiant) {
        Optional<CompteBancaire> compteTrouve = gestionnaireComptes.getCompte(numeroIdentifiant);

        return compteTrouve
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/comptes")
    public CompteBancaire creerNouveauCompte(@RequestBody CompteBancaire donneesCompte) {
        return gestionnaireComptes.createCompte(donneesCompte);
    }

    @PostMapping("/comptes/virement")
    public ResponseEntity<Map<String, String>> effectuerVirement(
            @RequestBody Map<String, Object> parametresVirement) {

        Long idCompteEmetteur = Long.valueOf(parametresVirement.get("compteSourceId").toString());
        Long idCompteBeneficiaire = Long.valueOf(parametresVirement.get("compteDestId").toString());
        double montantTransfert = Double.parseDouble(parametresVirement.get("montant").toString());

        boolean virementReussi = gestionnaireComptes.virement(
                idCompteEmetteur,
                idCompteBeneficiaire,
                montantTransfert
        );

        if (virementReussi) {
            return ResponseEntity.ok(
                    Map.of("message", "Virement effectué avec succès")
            );
        } else {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message",
                            "Virement échoué - Solde insuffisant ou comptes invalides"
                    ));
        }
    }
}