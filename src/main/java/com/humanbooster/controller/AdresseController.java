package com.humanbooster.controller;

import com.humanbooster.model.Adresse;
import com.humanbooster.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
public class AdresseController {

    private final AdresseService adresseService;

    @Autowired
    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping
    public List<Adresse> getAllAdresses() {
        return adresseService.getAllAdresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable int id) {
        return adresseService.getAdresseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveAdresse(@RequestBody Adresse adresse) {
        adresseService.saveAdresse(adresse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable int id, @RequestBody Adresse adresse) {
        return adresseService.getAdresseById(id)
                .map(existingAdresse -> adresseService.updateAdresse(adresse)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Adresse> deleteAdresseById(@PathVariable int id) {
        return adresseService.deleteAdresseById(id)
                .map(adresse -> ResponseEntity.ok().body(adresse))
                .orElse(ResponseEntity.notFound().build());
    }
}