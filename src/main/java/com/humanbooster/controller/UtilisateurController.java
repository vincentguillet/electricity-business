package com.humanbooster.controller;

import com.humanbooster.model.Utilisateur;
import com.humanbooster.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
        return utilisateurService.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurService.saveUtilisateur(utilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.getUtilisateurById(id)
                .map(existingUtilisateur -> utilisateurService.updateUtilisateur(utilisateur)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Utilisateur> deleteUtilisateurById(@PathVariable int id) {
        return utilisateurService.deleteUtilisateurById(id)
                .map(utilisateur -> ResponseEntity.ok().body(utilisateur))
                .orElse(ResponseEntity.notFound().build());
    }
}