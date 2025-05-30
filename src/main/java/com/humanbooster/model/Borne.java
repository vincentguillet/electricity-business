package com.humanbooster.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "bornes")
public class Borne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int numero;

    @ManyToOne
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    private double tarifMinute;

    private int puissance;

    private boolean murale;

    private boolean disponible;

    private String instructions;

    @OneToMany
    private List<Reservation> reservations = new ArrayList<>();

    public Borne() {}

    public Borne(int numero, double tarifMinute, int puissance, String instructions) {
        this.numero = numero;
        this.tarifMinute = tarifMinute;
        this.puissance = puissance;
        this.murale = false;
        this.disponible = true;
        this.instructions = instructions;
    }
}
