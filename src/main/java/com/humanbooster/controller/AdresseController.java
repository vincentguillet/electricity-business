package com.humanbooster.controller;

import com.humanbooster.model.Adresse;
import com.humanbooster.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdresseController {

    private final AdresseService adresseService;

    @Autowired
    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping("/adresses")
    public List<Adresse> getAllAdresses() {
        return adresseService.getAllAdresses();
    }

    @GetMapping("/adresses/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable int id) {
        return adresseService.getAdresseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adresses")
    public void saveAdresse(@RequestBody Adresse adresse) {
        adresseService.saveAdresse(adresse);
    }

    @PutMapping("/adresses/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable int id, @RequestBody Adresse adresse) {
        return adresseService.getAdresseById(id)
                .map(existingAdresse -> adresseService.updateAdresse(adresse)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/adresses/{id}")
    public ResponseEntity<Adresse> deleteAdresseById(@PathVariable int id) {
        return adresseService.deleteAdresseById(id)
                .map(adresse -> ResponseEntity.ok().body(adresse))
                .orElse(ResponseEntity.notFound().build());
    }
}