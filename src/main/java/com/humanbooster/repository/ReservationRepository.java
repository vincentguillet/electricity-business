package com.humanbooster.repository;

import com.humanbooster.model.Reservation;
import com.humanbooster.model.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
}
