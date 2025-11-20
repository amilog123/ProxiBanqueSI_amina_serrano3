package org.formation.hibernate.proxibanquesi_amina_serrano2.service;

import org.formation.hibernate.proxibanquesi_amina_serrano2.model.CompteBancaire;

import java.util.List;
import java.util.Optional;

public interface CompteService {
    List<CompteBancaire> getComptes();
    List<CompteBancaire> getComptesByClientId(Long clientId);
    CompteBancaire createCompte(CompteBancaire compte);
    Optional<CompteBancaire> getCompte(Long id);
    boolean virement(Long compteSourceId, Long compteDestId, double montant);
}