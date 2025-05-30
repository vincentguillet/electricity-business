package com.humanbooster.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ReservationId {

    @Column(name = "utilisateur_id")
    private int utilisateurId;

    @Column(name = "borne_id")
    private int borneId;

    public ReservationId() {}

    public ReservationId(int utilisateurId, int borneId) {
        this.utilisateurId = utilisateurId;
        this.borneId = borneId;
    }
}
