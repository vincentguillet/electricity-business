package fr.vguillet.electricitybusiness.Mapper.app;

import fr.vguillet.electricitybusiness.Mapper.user.UserMapper;
import fr.vguillet.electricitybusiness.dto.app.VehicleDTO;
import fr.vguillet.electricitybusiness.model.app.PlugType;
import fr.vguillet.electricitybusiness.model.app.Vehicle;

public class VehicleMapper {

    public static Vehicle fromDto(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setPlugType(vehicleDTO.getPlugType() != null ? PlugType.valueOf(vehicleDTO.getPlugType()) : null);
        vehicle.setBatteryCapacity(vehicleDTO.getBatteryCapacity());
        vehicle.setOwner(vehicleDTO.getOwnerDTO() != null ? UserMapper.fromDto(vehicleDTO.getOwnerDTO()) : null);
        return vehicle;
    }

    public static VehicleDTO toDto(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setBrand(vehicle.getBrand());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setYear(vehicle.getYear());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setLicensePlate(vehicle.getLicensePlate());
        vehicleDTO.setPlugType(vehicle.getPlugType() != null ? vehicle.getPlugType().getDisplayName() : null);
        vehicleDTO.setBatteryCapacity(vehicle.getBatteryCapacity());
        vehicleDTO.setOwnerDTO(vehicle.getOwner() != null ? UserMapper.toDto(vehicle.getOwner()) : null);
        return vehicleDTO;
    }
}
