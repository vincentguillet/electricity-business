package com.humanbooster.service;

import com.humanbooster.model.Reservation;
import com.humanbooster.model.ReservationId;
import com.humanbooster.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservationById(ReservationId id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public Optional<Reservation> updateReservation(Reservation reservation) {
        return reservationRepository.findById(reservation.getId()).map(existingReservation -> {
            existingReservation.setNumeroReservation(reservation.getNumeroReservation());
            existingReservation.setDateDebut(reservation.getDateDebut());
            existingReservation.setDateFin(reservation.getDateFin());
            existingReservation.setStatut(reservation.getStatut());
            existingReservation.setNote(reservation.getNote());
            existingReservation.setCommentaire(reservation.getCommentaire());
            return reservationRepository.save(existingReservation);
        });
    }

    @Transactional
    public Optional<Reservation> deleteReservationById(ReservationId id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservation.ifPresent(reservationRepository::delete);
        return reservation;
    }
}
