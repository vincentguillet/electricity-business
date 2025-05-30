package com.humanbooster.controller;

import com.humanbooster.model.Borne;
import com.humanbooster.service.BorneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorneController {

    private final BorneService borneService;

    @Autowired
    public BorneController(BorneService borneService) {
        this.borneService = borneService;
    }

    @GetMapping("/bornes")
    public List<Borne> getAllBornes() {
        return borneService.getAllBornes();
    }

    @GetMapping("/bornes/{id}")
    public ResponseEntity<Borne> getBorneById(@PathVariable int id) {
        return borneService.getBorneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/bornes")
    public void saveBorne(@RequestBody Borne borne) {
        borneService.saveBorne(borne);
    }

    @PutMapping("/bornes/{id}")
    public ResponseEntity<Borne> updateBorne(@PathVariable int id, @RequestBody Borne borne) {
        return borneService.getBorneById(id)
                .map(existingBorne -> borneService.updateBorne(borne)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/bornes/{id}")
    public ResponseEntity<Borne> deleteBorneById(@PathVariable int id) {
        return borneService.deleteBorneById(id)
                .map(borne -> ResponseEntity.ok().body(borne))
                .orElse(ResponseEntity.notFound().build());
    }
}