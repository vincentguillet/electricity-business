package fr.vguillet.electricitybusiness.controller.app;

import fr.vguillet.electricitybusiness.Mapper.app.ReservationMapper;
import fr.vguillet.electricitybusiness.dto.app.ReservationDTO;
import fr.vguillet.electricitybusiness.model.app.reservation.ReservationId;
import fr.vguillet.electricitybusiness.service.app.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations().stream()
                .map(ReservationMapper::toDto)
                .toList();
    }

    @GetMapping("/user/{user_id}/station/{station_id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long user_id, @PathVariable Long station_id) {
        ReservationId id = new ReservationId(user_id, station_id);
        return reservationService.getReservationById(id)
                .map(reservation -> ResponseEntity.ok(ReservationMapper.toDto(reservation)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveReservation(@RequestBody ReservationDTO reservationDTO) {
        reservationService.saveReservation(ReservationMapper.fromDto(reservationDTO));
    }

    @PutMapping("/user/{user_id}/station/{station_id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long user_id, @PathVariable Long station_id, @RequestBody ReservationDTO reservationDTO) {
        ReservationId id = new ReservationId(user_id, station_id);
        return reservationService.getReservationById(id)
                .map(existingReservation -> reservationService.updateReservation(ReservationMapper.fromDto(reservationDTO))
                        .map(reservation -> ResponseEntity.ok(ReservationMapper.toDto(reservation)))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{user_id}/station/{station_id}")
    public ResponseEntity<ReservationDTO> deleteReservationById(@PathVariable Long user_id, @PathVariable Long station_id) {
        ReservationId id = new ReservationId(user_id, station_id);
        return reservationService.deleteReservationById(id)
                .map(reservation -> ResponseEntity.ok(ReservationMapper.toDto(reservation)))
                .orElse(ResponseEntity.notFound().build());
    }
}
