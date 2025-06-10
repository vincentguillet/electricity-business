package com.humanbooster.service;

import com.humanbooster.model.Utilisateur;
import com.humanbooster.model.Vehicule;
import com.humanbooster.repository.UtilisateurRepository;
import com.humanbooster.repository.VehiculeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public VehiculeService(VehiculeRepository vehiculeRepository, UtilisateurRepository utilisateurRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional
    public void saveVehicule(Vehicule vehicule) {

        Utilisateur proprietaire = vehicule.getProprietaire();

        if (proprietaire != null && proprietaire.getId() == null) {
            // Si le propriétaire (Utilisateur) n'existe pas, on le save
            proprietaire = utilisateurRepository.save(proprietaire);
            vehicule.setProprietaire(proprietaire);
        }

        vehiculeRepository.save(vehicule);
    }

    public Optional<Vehicule> getVehiculeById(int id) {
        return vehiculeRepository.findById(id);
    }

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Transactional
    public Optional<Vehicule> updateVehicule(Vehicule vehicule) {
        return vehiculeRepository.findById(vehicule.getId()).map(existingVehicule -> {
            existingVehicule.setMarque(vehicule.getMarque());
            existingVehicule.setModele(vehicule.getModele());
            existingVehicule.setCouleur(vehicule.getCouleur());
            existingVehicule.setAnnee(vehicule.getAnnee());
            existingVehicule.setImmatriculation(vehicule.getImmatriculation());
            existingVehicule.setPuissance(vehicule.getPuissance());
            existingVehicule.setTypePrise(vehicule.getTypePrise());
            return vehiculeRepository.save(existingVehicule);
        });
    }

    @Transactional
    public Optional<Vehicule> deleteVehiculeById(int id) {
        Optional<Vehicule> vehicule = vehiculeRepository.findById(id);
        vehicule.ifPresent(vehiculeRepository::delete);
        return vehicule;
    }
}
