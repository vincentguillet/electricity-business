package com.humanbooster.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String nom;

    private String prenom;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Numéro de téléphone invalide")
    private String telephone;

    private LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse; // Ne sert que pour la facturation côté utilisateur

    @Email
    @NotBlank
    private String email;

    @Column(name = "mot_de_passe")
    @NotBlank
    private String motDePasse;

    private File iban;

    private boolean banni;

    private boolean enVacances;

    @OneToMany(mappedBy = "utilisateur")
    private List<Reservation> reservations = new ArrayList<>();

    public Utilisateur() {}

    public Utilisateur(String nom, String prenom, String telephone, LocalDate dateNaissance, Adresse adresse, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.email = email;
        this.motDePasse = motDePasse;
        this.banni = false;
        this.enVacances = false;
    }
}
