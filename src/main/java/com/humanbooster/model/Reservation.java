package com.humanbooster.model;

import com.humanbooster.model.enums.StatutReservation;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {

    @EmbeddedId
    private ReservationId id = new ReservationId();

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", insertable = false, updatable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "borne_id", insertable = false, updatable = false)
    private Borne borne;

    private String numeroReservation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutReservation statut;
    private short note;
    private String commentaire;

    public Reservation() {}

    public Reservation(Utilisateur utilisateur, Borne borne, String numeroReservation, LocalDate dateDebut, LocalDate dateFin, StatutReservation statut) {
        this.utilisateur = utilisateur;
        this.borne = borne;
        this.numeroReservation = numeroReservation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }
}
