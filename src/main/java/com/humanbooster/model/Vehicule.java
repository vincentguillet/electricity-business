package com.humanbooster.model;

import com.humanbooster.model.enums.Couleur;
import com.humanbooster.model.enums.TypePrise;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicules")
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String marque;
    private String modele;
    private Couleur couleur;
    private int annee;
    private String immatriculation;
    private int puissance;

    private TypePrise typePrise;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    private Utilisateur proprietaire;

    public Vehicule() {}

    public Vehicule(String marque, String modele, Couleur couleur, int annee, String immatriculation, int puissance) {
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.annee = annee;
        this.immatriculation = immatriculation;
        this.puissance = puissance;
    }
}
