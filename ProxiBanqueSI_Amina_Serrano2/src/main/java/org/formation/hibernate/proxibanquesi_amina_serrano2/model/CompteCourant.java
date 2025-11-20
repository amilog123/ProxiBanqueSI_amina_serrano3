package org.formation.hibernate.proxibanquesi_amina_serrano2.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("COURANT")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CompteCourant extends CompteBancaire {

    private double decouvertAutorise = 4000.0;

    public CompteCourant(String numeroCompte, double solde, Client client) {
        super(null, numeroCompte, solde, LocalDate.now(), client);
    }
}