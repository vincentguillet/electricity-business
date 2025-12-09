package fr.vguillet.electricitybusiness.service.app;

import fr.vguillet.electricitybusiness.model.app.Station;
import fr.vguillet.electricitybusiness.repository.app.StationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    @Transactional
    public void saveStation(Station station) {
        if (station.getId() == null) {
            stationRepository.save(station);
        }
    }

    @Transactional
    public Optional<Station> updateStation(Station station) {
        return stationRepository.findById(station.getId()).map(existingStation -> {
            existingStation.setNumber(station.getNumber());
            existingStation.setLatitude(station.getLatitude());
            existingStation.setLongitude(station.getLongitude());
            existingStation.setInstructions(station.getInstructions());
            existingStation.setPower(station.getPower());
            existingStation.setWallMounted(station.isWallMounted());
            existingStation.setPlugType(station.getPlugType());
            existingStation.setPerHourPrice(station.getPerHourPrice());
            existingStation.setAvailable(station.isAvailable());
            existingStation.setPlace(station.getPlace());
            existingStation.setReservations(station.getReservations());
            return stationRepository.save(existingStation);
        });
    }

    @Transactional
    public Optional<Station> deleteStationById(Long id) {
        Optional<Station> station = stationRepository.findById(id);
        station.ifPresent(stationRepository::delete);
        return station;
    }
}
