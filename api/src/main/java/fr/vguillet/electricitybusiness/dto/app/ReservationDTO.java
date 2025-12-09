package fr.vguillet.electricitybusiness.dto.app;

import fr.vguillet.electricitybusiness.dto.user.UserDTO;
import fr.vguillet.electricitybusiness.model.app.reservation.ReservationId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private ReservationId reservationId;
    private UserDTO userDTO;
    private StationDTO stationDTO;
    private String number;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private short note;
    private String comment;
}
