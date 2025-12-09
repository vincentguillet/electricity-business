package fr.vguillet.electricitybusiness.repository.app;

import fr.vguillet.electricitybusiness.model.app.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
