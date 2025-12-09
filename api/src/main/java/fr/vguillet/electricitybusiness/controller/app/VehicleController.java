package fr.vguillet.electricitybusiness.controller.app;

import fr.vguillet.electricitybusiness.Mapper.app.VehicleMapper;
import fr.vguillet.electricitybusiness.dto.app.VehicleDTO;
import fr.vguillet.electricitybusiness.service.app.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles().stream()
                .map(VehicleMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> ResponseEntity.ok(VehicleMapper.toDto(vehicle)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.saveVehicle(VehicleMapper.fromDto(vehicleDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.getVehicleById(id)
                .map(existingVehicle -> vehicleService.updateVehicle(VehicleMapper.fromDto(vehicleDTO))
                        .map(vehicle -> ResponseEntity.ok(VehicleMapper.toDto(vehicle)))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDTO> deleteVehicleById(@PathVariable Long id) {
        return vehicleService.deleteVehicleById(id)
                .map(vehicle -> ResponseEntity.ok(VehicleMapper.toDto(vehicle)))
                .orElse(ResponseEntity.notFound().build());
    }
}
