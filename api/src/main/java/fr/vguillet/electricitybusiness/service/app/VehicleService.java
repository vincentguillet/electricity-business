package fr.vguillet.electricitybusiness.service.app;

import fr.vguillet.electricitybusiness.model.app.Vehicle;
import fr.vguillet.electricitybusiness.repository.app.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Transactional
    public void saveVehicle(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            vehicleRepository.save(vehicle);
        }
    }

    @Transactional
    public Optional<Vehicle> updateVehicle(Vehicle vehicle) {
        return vehicleRepository.findById(vehicle.getId()).map(existingVehicle -> {
            existingVehicle.setBrand(vehicle.getBrand());
            existingVehicle.setModel(vehicle.getModel());
            existingVehicle.setYear(vehicle.getYear());
            existingVehicle.setColor(vehicle.getColor());
            existingVehicle.setLicensePlate(vehicle.getLicensePlate());
            existingVehicle.setPlugType(vehicle.getPlugType());
            existingVehicle.setBatteryCapacity(vehicle.getBatteryCapacity());
            existingVehicle.setOwner(vehicle.getOwner());
            return vehicleRepository.save(existingVehicle);
        });
    }

    @Transactional
    public Optional<Vehicle> deleteVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        vehicle.ifPresent(vehicleRepository::delete);
        return vehicle;
    }
}
