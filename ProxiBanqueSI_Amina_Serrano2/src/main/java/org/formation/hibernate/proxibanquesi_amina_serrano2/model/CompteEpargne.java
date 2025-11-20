package org.formation.hibernate.proxibanquesi_amina_serrano2.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("EPARGNE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CompteEpargne extends CompteBancaire {

    private double tauxRemuneration = 0.1;

    public CompteEpargne(String numeroCompte, double solde, Client client) {
        super(null, numeroCompte, solde, LocalDate.now(), client);
    }
}