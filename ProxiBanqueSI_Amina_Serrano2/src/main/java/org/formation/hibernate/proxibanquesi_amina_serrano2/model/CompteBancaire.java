package org.formation.hibernate.proxibanquesi_amina_serrano2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CompteBancaire {

    @Id
    @GeneratedValue
    private Long id;

    private String numeroCompte;
    private double solde;
    private LocalDate dateOuverture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore  // ← AJOUTE CETTE LIGNE
    private Client client;
}