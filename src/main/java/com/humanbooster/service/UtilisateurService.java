package com.humanbooster.service;

import com.humanbooster.model.Adresse;
import com.humanbooster.model.Utilisateur;
import com.humanbooster.repository.AdresseRepository;
import com.humanbooster.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final AdresseRepository adresseRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, AdresseRepository adresseRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.adresseRepository = adresseRepository;
    }

    @Transactional
    public void saveUtilisateur(Utilisateur utilisateur) {

        Adresse adresseUtilisateur = utilisateur.getAdresse();

        if (utilisateur.getId() == null) {
            if(adresseUtilisateur != null && adresseUtilisateur.getId() == null) {
                // Si l'adresse de l'utilisateur n'existe pas en BDD, on la save
                adresseUtilisateur = adresseRepository.save(adresseUtilisateur);
                utilisateurRepository.save(utilisateur);
            }
        }

        utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> getUtilisateurById(int id) {
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Transactional
    public Optional<Utilisateur> updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.findById(utilisateur.getId()).map(existingUtilisateur -> {
            existingUtilisateur.setNom(utilisateur.getNom());
            existingUtilisateur.setPrenom(utilisateur.getPrenom());
            existingUtilisateur.setTelephone(utilisateur.getTelephone());
            existingUtilisateur.setDateNaissance(utilisateur.getDateNaissance());
            existingUtilisateur.setAdresse(utilisateur.getAdresse());
            existingUtilisateur.setEmail(utilisateur.getEmail());
            existingUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
            existingUtilisateur.setIban(utilisateur.getIban());
            existingUtilisateur.setBanni(utilisateur.isBanni());
            existingUtilisateur.setEnVacances(utilisateur.isEnVacances());
            return utilisateurRepository.save(existingUtilisateur);
        });
    }

    @Transactional
    public Optional<Utilisateur> deleteUtilisateurById(int id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        utilisateur.ifPresent(utilisateurRepository::delete);
        return utilisateur;
    }
}
