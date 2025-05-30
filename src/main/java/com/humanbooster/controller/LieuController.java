package com.humanbooster.controller;

import com.humanbooster.model.Lieu;
import com.humanbooster.service.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lieux")
public class LieuController {

    private final LieuService lieuService;

    @Autowired
    public LieuController(LieuService lieuService) {
        this.lieuService = lieuService;
    }

    @GetMapping
    public List<Lieu> getAllLieux() {
        return lieuService.getAllLieux();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lieu> getLieuById(@PathVariable int id) {
        return lieuService.getLieuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveLieu(@RequestBody Lieu lieu) {
        lieuService.saveLieu(lieu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lieu> updateLieu(@PathVariable int id, @RequestBody Lieu lieu) {
        return lieuService.getLieuById(id)
                .map(existingLieu -> lieuService.updateLieu(lieu)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Lieu> deleteLieuById(@PathVariable int id) {
        return lieuService.deleteLieuById(id)
                .map(lieu -> ResponseEntity.ok().body(lieu))
                .orElse(ResponseEntity.notFound().build());
    }
}