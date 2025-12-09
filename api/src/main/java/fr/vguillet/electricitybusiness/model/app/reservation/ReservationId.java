package fr.vguillet.electricitybusiness.model.app.reservation;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationId {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "station_id")
    private Long stationId;
}