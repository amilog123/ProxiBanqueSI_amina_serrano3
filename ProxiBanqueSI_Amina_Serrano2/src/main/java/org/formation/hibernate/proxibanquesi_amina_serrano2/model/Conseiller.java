package org.formation.hibernate.proxibanquesi_amina_serrano2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Conseiller {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;

    public Conseiller(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}