package com.humanbooster.controller;

import com.humanbooster.model.Vehicule;
import com.humanbooster.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @Autowired
    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping("/vehicules")
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("/vehicules/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable int id) {
        return vehiculeService.getVehiculeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/vehicules")
    public void saveVehicule(@RequestBody Vehicule vehicule) {
        vehiculeService.saveVehicule(vehicule);
    }

    @PutMapping("/vehicules/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable int id, @RequestBody Vehicule vehicule) {
        return vehiculeService.getVehiculeById(id)
                .map(existingVehicule -> vehiculeService.updateVehicule(vehicule)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/vehicules/{id}")
    public ResponseEntity<Vehicule> deleteVehiculeById(@PathVariable int id) {
        return vehiculeService.deleteVehiculeById(id)
                .map(vehicule -> ResponseEntity.ok().body(vehicule))
                .orElse(ResponseEntity.notFound().build());
    }
}