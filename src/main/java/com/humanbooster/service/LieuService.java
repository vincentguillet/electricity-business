package com.humanbooster.service;

import com.humanbooster.model.Lieu;
import com.humanbooster.repository.LieuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LieuService {

    private final LieuRepository lieuRepository;

    @Autowired
    public LieuService(LieuRepository lieuRepository) {
        this.lieuRepository = lieuRepository;
    }

    @Transactional
    public void saveLieu(Lieu lieu) {
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
