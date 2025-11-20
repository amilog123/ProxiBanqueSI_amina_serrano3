package org.formation.hibernate.proxibanquesi_amina_serrano2.repository;

import org.formation.hibernate.proxibanquesi_amina_serrano2.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {
    List<CompteBancaire> findByClientId(Long clientId);
}