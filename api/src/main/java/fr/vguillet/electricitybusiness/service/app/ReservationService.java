package fr.vguillet.electricitybusiness.service.app;

import fr.vguillet.electricitybusiness.model.app.reservation.Reservation;
import fr.vguillet.electricitybusiness.model.app.reservation.ReservationId;
import fr.vguillet.electricitybusiness.repository.app.ReservationRepository;
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

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(ReservationId id) {
        return reservationRepository.findById(id);
    }

    @Transactional
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Transactional
    public Optional<Reservation> updateReservation(Reservation reservation) {
        return reservationRepository.findById(reservation.getId()).map(existingReservation -> {
            existingReservation.setUser(reservation.getUser());
            existingReservation.setStation(reservation.getStation());
            existingReservation.setNumber(reservation.getNumber());
            existingReservation.setStartDate(reservation.getStartDate());
            existingReservation.setEndDate(reservation.getEndDate());
            existingReservation.setStatus(reservation.getStatus());
            existingReservation.setNote(reservation.getNote());
            existingReservation.setComment(reservation.getComment());
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
