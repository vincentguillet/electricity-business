package com.humanbooster.service;

import com.humanbooster.model.Adresse;
import com.humanbooster.model.Borne;
import com.humanbooster.model.Lieu;
import com.humanbooster.model.Utilisateur;
import com.humanbooster.repository.AdresseRepository;
import com.humanbooster.repository.BorneRepository;
import com.humanbooster.repository.LieuRepository;
import com.humanbooster.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BorneService {

    private final BorneRepository borneRepository;
    private final LieuRepository lieuRepository;
    private final AdresseRepository adresseRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public BorneService(BorneRepository borneRepository, LieuRepository lieuRepository,
                        AdresseRepository adresseRepository, UtilisateurRepository utilisateurRepository) {
        this.borneRepository = borneRepository;
        this.lieuRepository = lieuRepository;
        this.adresseRepository = adresseRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional
    public void saveBorne(Borne borne) {

        Lieu lieu = borne.getLieu();
        Adresse adresseLieu = lieu.getAdresse();
        Utilisateur utilisateur = lieu.getUtilisateur();
        Adresse adresseUtilisateur = utilisateur.getAdresse();

        if (borne.getLieu().getId() == null) {
            // Si le lieu n'existe pas, on le crée et on le save
            if (adresseLieu != null && adresseLieu.getId() == null) {
                adresseLieu = adresseRepository.save(adresseLieu);
                borne.getLieu().setAdresse(adresseLieu);
            }

            // Si l'utilisateur du lieu n'existe pas, on le crée et on le save
            if (utilisateur.getId() == null) {
                if(adresseUtilisateur != null && adresseUtilisateur.getId() == null) {
                    // Si l'adresse de l'utilisateur n'existe pas en BDD, on la save
                    adresseUtilisateur = adresseRepository.save(adresseUtilisateur);
                    borne.getLieu().getUtilisateur().setAdresse(adresseUtilisateur);
                    utilisateurRepository.save(utilisateur);
                }
            }
        }


        if (lieu.getId() == null) {
            lieu = lieuRepository.save(lieu);
            borne.setLieu(lieu);
        }
        borneRepository.save(borne);
    }

    public Optional<Borne> getBorneById(int id) {
        return borneRepository.findById(id);
    }

    public List<Borne> getAllBornes() {
        return borneRepository.findAll();
    }

    @Transactional
    public Optional<Borne> updateBorne(Borne borne) {
        return borneRepository.findById(borne.getId()).map(existingBorne -> {
            existingBorne.setNumero(borne.getNumero());
            existingBorne.setLieu(borne.getLieu());
            existingBorne.setTarifMinute(borne.getTarifMinute());
            existingBorne.setPuissance(borne.getPuissance());
            existingBorne.setMurale(borne.isMurale());
            existingBorne.setDisponible(borne.isDisponible());
            existingBorne.setInstructions(borne.getInstructions());
            return borneRepository.save(existingBorne);
        });
    }

    @Transactional
    public Optional<Borne> deleteBorneById(int id) {
        Optional<Borne> borne = borneRepository.findById(id);
        borne.ifPresent(borneRepository::delete);
        return borne;
    }
}
