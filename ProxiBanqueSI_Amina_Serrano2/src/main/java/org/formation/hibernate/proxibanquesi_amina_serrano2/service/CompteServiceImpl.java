package org.formation.hibernate.proxibanquesi_amina_serrano2.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.formation.hibernate.proxibanquesi_amina_serrano2.model.*;
import org.formation.hibernate.proxibanquesi_amina_serrano2.repository.ClientRepository;
import org.formation.hibernate.proxibanquesi_amina_serrano2.repository.CompteBancaireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompteServiceImpl implements CompteService {

    private final CompteBancaireRepository daoComptes;
    private final ClientRepository daoClients;

    @PostConstruct
    private void initDb() {
        List<Client> listeClients = daoClients.findAll();

        if (!listeClients.isEmpty() && listeClients.size() >= 2) {
            Client proprietaire1 = listeClients.get(0);
            Client proprietaire2 = listeClients.get(1);

            CompteCourant compteCourantPremier = new CompteCourant();
            compteCourantPremier.setNumeroCompte("CC2025-001");
            compteCourantPremier.setSolde(8000.0);
            compteCourantPremier.setClient(proprietaire1);
            compteCourantPremier.setDateOuverture(java.time.LocalDate.now());
            compteCourantPremier.setDecouvertAutorise(2500.0);

            CompteEpargne compteEpargnePremier = new CompteEpargne();
            compteEpargnePremier.setNumeroCompte("CE2025-001");
            compteEpargnePremier.setSolde(12000.0);
            compteEpargnePremier.setClient(proprietaire1);
            compteEpargnePremier.setDateOuverture(java.time.LocalDate.now());
            compteEpargnePremier.setTauxRemuneration(0.035);

            CompteCourant compteCourantSecond = new CompteCourant();
            compteCourantSecond.setNumeroCompte("CC2025-002");
            compteCourantSecond.setSolde(4500.0);
            compteCourantSecond.setClient(proprietaire2);
            compteCourantSecond.setDateOuverture(java.time.LocalDate.now());
            compteCourantSecond.setDecouvertAutorise(1500.0);

            CompteEpargne compteEpargneSecond = new CompteEpargne();
            compteEpargneSecond.setNumeroCompte("CE2025-002");
            compteEpargneSecond.setSolde(18000.0);
            compteEpargneSecond.setClient(proprietaire2);
            compteEpargneSecond.setDateOuverture(java.time.LocalDate.now());
            compteEpargneSecond.setTauxRemuneration(0.04);

            daoComptes.saveAll(List.of(
                    compteCourantPremier,
                    compteEpargnePremier,
                    compteCourantSecond,
                    compteEpargneSecond
            ));
        }
    }

    @Override
    public List<CompteBancaire> getComptes() {
        return daoComptes.findAll();
    }

    @Override
    public List<CompteBancaire> getComptesByClientId(Long idClient) {
        return daoComptes.findByClientId(idClient);
    }

    @Override
    public CompteBancaire createCompte(CompteBancaire nouveauCompte) {
        return daoComptes.save(nouveauCompte);
    }

    @Override
    public Optional<CompteBancaire> getCompte(Long identifiantCompte) {
        return daoComptes.findById(identifiantCompte);
    }

    @Override
    @Transactional
    public boolean virement(Long idCompteDebit, Long idCompteCredit, double somme) {
        Optional<CompteBancaire> compteDebitOpt = daoComptes.findById(idCompteDebit);
        Optional<CompteBancaire> compteCreditOpt = daoComptes.findById(idCompteCredit);

        if (compteDebitOpt.isEmpty() || compteCreditOpt.isEmpty()) {
            return false;
        }

        CompteBancaire compteADebiter = compteDebitOpt.get();
        CompteBancaire compteACrediter = compteCreditOpt.get();

        double fondsDispo = compteADebiter.getSolde();
        if (compteADebiter instanceof CompteCourant) {
            CompteCourant compteCourantDebit = (CompteCourant) compteADebiter;
            fondsDispo += compteCourantDebit.getDecouvertAutorise();
        }

        if (fondsDispo < somme) {
            return false;
        }

        double nouveauSoldeDebit = compteADebiter.getSolde() - somme;
        double nouveauSoldeCredit = compteACrediter.getSolde() + somme;

        compteADebiter.setSolde(nouveauSoldeDebit);
        compteACrediter.setSolde(nouveauSoldeCredit);

        daoComptes.save(compteADebiter);
        daoComptes.save(compteACrediter);

        return true;
    }
}