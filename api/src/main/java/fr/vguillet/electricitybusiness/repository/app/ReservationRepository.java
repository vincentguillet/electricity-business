package fr.vguillet.electricitybusiness.repository.app;

import fr.vguillet.electricitybusiness.model.app.reservation.Reservation;
import fr.vguillet.electricitybusiness.model.app.reservation.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
}
