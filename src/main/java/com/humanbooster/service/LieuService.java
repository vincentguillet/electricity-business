package com.humanbooster.service;

import com.humanbooster.model.Adresse;
import com.humanbooster.model.Lieu;
import com.humanbooster.model.Utilisateur;
import com.humanbooster.repository.AdresseRepository;
import com.humanbooster.repository.LieuRepository;
import com.humanbooster.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LieuService {

    private final LieuRepository lieuRepository;
    private final AdresseRepository adresseRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public LieuService(LieuRepository lieuRepository, AdresseRepository adresseRepository, UtilisateurRepository utilisateurRepository) {
        this.lieuRepository = lieuRepository;
        this.adresseRepository = adresseRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional
    public void saveLieu(Lieu lieu) {

        Adresse adresseLieu = lieu.getAdresse();
        Utilisateur utilisateur = lieu.getUtilisateur();
        Adresse adresseUtilisateur = utilisateur.getAdresse();
        
        if (adresseLieu != null && adresseLieu.getId() == null) {
            adresseLieu = adresseRepository.save(adresseLieu);
            lieu.setAdresse(adresseLieu);
        }

        // Si l'utilisateur du lieu n'existe pas, on le crée et on le save
        if (utilisateur.getId() == null) {
            if(adresseUtilisateur != null && adresseUtilisateur.getId() == null) {
                // Si l'adresse de l'utilisateur n'existe pas en BDD, on la save
                adresseUtilisateur = adresseRepository.save(adresseUtilisateur);
                lieu.getUtilisateur().setAdresse(adresseUtilisateur);
                utilisateurRepository.save(utilisateur);
            }
        }
        lieuRepository.save(lieu);
    }

    public Optional<Lieu> getLieuById(int id) {
        return lieuRepository.findById(id);
    }

    public List<Lieu> getAllLieux() {
        return lieuRepository.findAll();
    }

    @Transactional
    public Optional<Lieu> updateLieu(Lieu lieu) {
        return lieuRepository.findById(lieu.getId()).map(existingLieu -> {
            existingLieu.setNom(lieu.getNom());
            existingLieu.setAdresse(lieu.getAdresse());
            return lieuRepository.save(existingLieu);
        });
    }

    @Transactional
    public Optional<Lieu> deleteLieuById(int id) {
        Optional<Lieu> lieu = lieuRepository.findById(id);
        lieu.ifPresent(lieuRepository::delete);
        return lieu;
    }
}
