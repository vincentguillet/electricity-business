package fr.vguillet.electricitybusiness.dto.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDTO {
    private Long id;
    private Integer number;
    private String latitude;
    private String longitude;
    private String instructions;
    private Integer power; // in kW
    private boolean wallMounted;
    private String plugType;
    private double perHourPrice;
    private boolean available;
    private PlaceDTO placeDTO;
    private List<ReservationDTO> reservationsDTO;
}
