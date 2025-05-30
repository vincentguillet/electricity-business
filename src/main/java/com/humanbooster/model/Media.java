package com.humanbooster.model;

import com.humanbooster.model.enums.TypeMedia;
import jakarta.persistence.*;
import lombok.Data;

import java.net.URL;

@Entity
@Data
@Table(name = "medias")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private TypeMedia type;

    private URL url;

    private double taille;

    public Media(){}

    public Media(String nom, TypeMedia type, URL url, double taille) {
        this.nom = nom;
        this.type = type;
        this.url = url;
        this.taille = taille;
    }
}
