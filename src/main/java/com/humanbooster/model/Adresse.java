package com.humanbooster.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "adresses")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String adresseBase;
    private String complement;
    private String codePostal;
    private String ville;

    public Adresse() {}

    public Adresse(String adresseBase, String complement, String codePostal, String ville) {
        this.adresseBase = adresseBase;
        this.complement = complement;
        this.codePostal = codePostal;
        this.ville = ville;
    }
}
