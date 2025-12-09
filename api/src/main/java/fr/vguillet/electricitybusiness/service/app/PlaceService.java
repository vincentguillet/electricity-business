package fr.vguillet.electricitybusiness.service.app;

import fr.vguillet.electricitybusiness.model.app.Place;
import fr.vguillet.electricitybusiness.repository.app.PlaceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    @Transactional
    public void savePlace(Place place) {
        if (place.getId() == null) {
            placeRepository.save(place);
        }
    }

    @Transactional
    public Optional<Place> updatePlace(Place place) {
        return placeRepository.findById(place.getId()).map(existingPlace -> {
            existingPlace.setLabel(place.getLabel());
            existingPlace.setInstructions(place.getInstructions());
            existingPlace.setOwner(place.getOwner());
            existingPlace.setAddress(place.getAddress());
            return placeRepository.save(existingPlace);
        });
    }

    @Transactional
    public Optional<Place> deletePlaceById(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        place.ifPresent(placeRepository::delete);
        return place;
    }
}
