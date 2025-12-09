package fr.vguillet.electricitybusiness.controller.app;

import fr.vguillet.electricitybusiness.Mapper.app.PlaceMapper;
import fr.vguillet.electricitybusiness.dto.app.PlaceDTO;
import fr.vguillet.electricitybusiness.service.app.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public List<PlaceDTO> getAllPlaces() {
        return placeService.getAllPlaces().stream()
                .map(PlaceMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getPlaceById(@PathVariable Long id) {
        return placeService.getPlaceById(id)
                .map(place -> ResponseEntity.ok(PlaceMapper.toDto(place)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void savePlace(@RequestBody PlaceDTO placeDTO) {
        placeService.savePlace(PlaceMapper.fromDto(placeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceDTO> updatePlace(@PathVariable Long id, @RequestBody PlaceDTO placeDTO) {
        return placeService.getPlaceById(id)
                .map(existingPlace -> placeService.updatePlace(PlaceMapper.fromDto(placeDTO))
                        .map(place -> ResponseEntity.ok(PlaceMapper.toDto(place)))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlaceDTO> deletePlaceById(@PathVariable Long id) {
        return placeService.deletePlaceById(id)
                .map(place -> ResponseEntity.ok(PlaceMapper.toDto(place)))
                .orElse(ResponseEntity.notFound().build());
    }
}
