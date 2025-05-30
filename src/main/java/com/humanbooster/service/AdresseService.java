package com.humanbooster.service;

import com.humanbooster.model.Adresse;
import com.humanbooster.repository.AdresseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    private final AdresseRepository adresseRepository;

    @Autowired
    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    @Transactional
    public void saveAdresse(Adresse adresse) {
        adresseRepository.save(adresse);
    }

    public Optional<Adresse> getAdresseById(int id) {
        return adresseRepository.findById(id);
    }

    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    @Transactional
    public Optional<Adresse> updateAdresse(Adresse adresse) {
        return adresseRepository.findById(adresse.getId()).map(existingAdresse -> {
            existingAdresse.setAdresseBase(adresse.getAdresseBase());
            existingAdresse.setCodePostal(adresse.getCodePostal());
            existingAdresse.setVille(adresse.getVille());
            return adresseRepository.save(existingAdresse);
        });
    }

    @Transactional
    public Optional<Adresse> deleteAdresseById(int id) {
        Optional<Adresse> adresse = adresseRepository.findById(id);
        adresse.ifPresent(adresseRepository::delete);
        return adresse;
    }
}
