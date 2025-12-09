package fr.vguillet.electricitybusiness.controller.app;

import fr.vguillet.electricitybusiness.Mapper.app.StationMapper;
import fr.vguillet.electricitybusiness.dto.app.StationDTO;
import fr.vguillet.electricitybusiness.service.app.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public List<StationDTO> getAllStations() {
        return stationService.getAllStations().stream()
                .map(StationMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDTO> getStationById(@PathVariable Long id) {
        return stationService.getStationById(id)
                .map(station -> ResponseEntity.ok(StationMapper.toDto(station)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveStation(@RequestBody StationDTO stationDTO) {
        stationService.saveStation(StationMapper.fromDto(stationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationDTO> updateStation(@PathVariable Long id, @RequestBody StationDTO stationDTO) {
        return stationService.getStationById(id)
                .map(existingStation -> stationService.updateStation(StationMapper.fromDto(stationDTO))
                        .map(station -> ResponseEntity.ok(StationMapper.toDto(station)))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StationDTO> deleteStationById(@PathVariable Long id) {
        return stationService.deleteStationById(id)
                .map(station -> ResponseEntity.ok(StationMapper.toDto(station)))
                .orElse(ResponseEntity.notFound().build());
    }
}
