package com.humanbooster.service;

import com.humanbooster.model.*;
import com.humanbooster.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AdresseRepository adresseRepository;
    private final BorneRepository borneRepository;
    private final LieuRepository lieuRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UtilisateurRepository utilisateurRepository, AdresseRepository adresseRepository, BorneRepository borneRepository, LieuRepository lieuRepository) {
        this.reservationRepository = reservationRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.adresseRepository = adresseRepository;
        this.borneRepository = borneRepository;
        this.lieuRepository = lieuRepository;
    }

    @Transactional
    public void saveReservation(Reservation reservation) {

        Borne borne = reservation.getBorne();
        Lieu lieu = borne.getLieu();
        Adresse adresseLieu = lieu.getAdresse();
        Utilisateur utilisateur = lieu.getUtilisateur();
        Adresse adresseUtilisateur = utilisateur.getAdresse();

        if (borne.getLieu().getId() == null) {
            // Si le lieu n'existe pas, on le crée et on le save
            if (adresseLieu != null && adresseLieu.getId() == null) {
                adresseLieu = adresseRepository.save(adresseLieu);
                borne.getLieu().setAdresse(adresseLieu);
            }

            // Si l'utilisateur du lieu n'existe pas, on le crée et on le save
            if (utilisateur.getId() == null) {
                if(adresseUtilisateur != null && adresseUtilisateur.getId() == null) {
                    // Si l'adresse de l'utilisateur n'existe pas en BDD, on la save
                    adresseUtilisateur = adresseRepository.save(adresseUtilisateur);
                    borne.getLieu().getUtilisateur().setAdresse(adresseUtilisateur);
                    utilisateurRepository.save(utilisateur);
                    reservation.setUtilisateur(utilisateur);
                }
            }
        }

        if (lieu.getId() == null) {
            lieu = lieuRepository.save(lieu);
            borne.setLieu(lieu);
        }
        borneRepository.save(borne);
        reservation.setBorne(borne);

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
