package com.humanbooster.service;

import com.humanbooster.model.Borne;
import com.humanbooster.repository.BorneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BorneService {

    private final BorneRepository borneRepository;

    @Autowired
    public BorneService(BorneRepository borneRepository) {
        this.borneRepository = borneRepository;
    }

    @Transactional
    public void saveBorne(Borne borne) {
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
