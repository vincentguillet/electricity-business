package fr.vguillet.electricitybusiness.dto.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private Long id;
    private String brand;
    private String model;
    private String year;
    private String color;
    private String licensePlate;
    private String plugType;
    private Integer batteryCapacity;
}
