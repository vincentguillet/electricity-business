package com.humanbooster.controller;

import com.humanbooster.model.Reservation;
import com.humanbooster.model.ReservationId;
import com.humanbooster.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/utilisateur/{utilisateurId}/borne/{borneId}")
    public ResponseEntity<Reservation> getReservationById(
            @PathVariable int utilisateurId,
            @PathVariable int borneId) {
        ReservationId id = new ReservationId(utilisateurId, borneId);
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveReservation(@RequestBody Reservation reservation) {
        reservationService.saveReservation(reservation);
    }

    @PutMapping("/utilisateur/{utilisateurId}/borne/{borneId}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable int utilisateurId,
            @PathVariable int borneId,
            @RequestBody Reservation reservation) {
        ReservationId id = new ReservationId(utilisateurId, borneId);
        return reservationService.getReservationById(id)
                .map(existingReservation -> reservationService.updateReservation(reservation)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/utilisateur/{utilisateurId}/borne/{borneId}")
    public ResponseEntity<Reservation> deleteReservationById(
            @PathVariable int utilisateurId,
            @PathVariable int borneId) {
        ReservationId id = new ReservationId(utilisateurId, borneId);
        return reservationService.deleteReservationById(id)
                .map(reservation -> ResponseEntity.ok().body(reservation))
                .orElse(ResponseEntity.notFound().build());
    }
}