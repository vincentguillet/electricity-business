package com.humanbooster.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "lieux")
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @OneToOne
    private Adresse adresse;

    @OneToOne
    private Utilisateur utilisateur;

    public Lieu() {}

    public Lieu(String nom) {
        this.nom = nom;
    }

    public Lieu(String nom, Adresse adresse, Utilisateur utilisateur) {
        this.nom = nom;
        this.adresse = adresse;
        this.utilisateur = utilisateur;
    }
}
