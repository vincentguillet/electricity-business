package fr.vguillet.electricitybusiness.Mapper.app;

import fr.vguillet.electricitybusiness.Mapper.user.UserMapper;
import fr.vguillet.electricitybusiness.dto.app.ReservationDTO;
import fr.vguillet.electricitybusiness.model.app.reservation.Reservation;
import fr.vguillet.electricitybusiness.model.app.reservation.Status;

public class ReservationMapper {

    public static Reservation fromDto(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getReservationId());
        reservation.setUser(reservationDTO.getUserDTO() != null ? UserMapper.fromDto(reservationDTO.getUserDTO()) : null);
        reservation.setStation(reservationDTO.getStationDTO() != null ? StationMapper.fromDto(reservationDTO.getStationDTO()) : null);
        reservation.setNumber(reservationDTO.getNumber());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStatus(reservationDTO.getStatus() != null ? Status.valueOf(reservationDTO.getStatus()) : null);
        reservation.setNote(reservationDTO.getNote());
        reservation.setComment(reservationDTO.getComment());
        return reservation;
    }

    public static ReservationDTO toDto(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getId());
        reservationDTO.setUserDTO(reservation.getUser() != null ? UserMapper.toDto(reservation.getUser()) : null);
        reservationDTO.setStationDTO(reservation.getStation() != null ? StationMapper.toDto(reservation.getStation()) : null);
        reservationDTO.setNumber(reservation.getNumber());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setStatus(reservation.getStatus() != null ? reservation.getStatus().name() : null);
        reservationDTO.setNote(reservation.getNote());
        reservationDTO.setComment(reservation.getComment());
        return reservationDTO;
    }
}
