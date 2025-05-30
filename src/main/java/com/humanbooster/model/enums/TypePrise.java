package com.humanbooster.model.enums;

import lombok.Getter;

@Getter
public enum TypePrise {

    TYPE_2("Type 2"),
    TYPE_3("Type 3"),
    CCS("CCS"),
    CHADEMO("Chademo");

    private final String nom;

    TypePrise(String nom) {
        this.nom = nom;
    }
}
