package fr.vguillet.electricitybusiness.Mapper.app;

import fr.vguillet.electricitybusiness.dto.app.StationDTO;
import fr.vguillet.electricitybusiness.model.app.PlugType;
import fr.vguillet.electricitybusiness.model.app.Station;

public class StationMapper {

    public static Station fromDto(StationDTO stationDTO) {
        Station station = new Station();
        station.setId(stationDTO.getId());
        station.setNumber(stationDTO.getNumber());
        station.setLatitude(stationDTO.getLatitude());
        station.setLongitude(stationDTO.getLongitude());
        station.setPower(stationDTO.getPower());
        station.setWallMounted(stationDTO.isWallMounted());
        station.setPlugType(stationDTO.getPlugType() != null ? PlugType.valueOf(stationDTO.getPlugType()) : null);
        station.setPerHourPrice(stationDTO.getPerHourPrice());
        station.setAvailable(stationDTO.isAvailable());
        station.setPlace(stationDTO.getPlaceDTO() != null ? PlaceMapper.fromDto(stationDTO.getPlaceDTO()) : null);
        station.setReservations(stationDTO.getReservationsDTO() != null ? stationDTO.getReservationsDTO()
                .stream()
                .map(ReservationMapper::fromDto)
                .toList() : null);
        return station;
    }

    public static StationDTO toDto(Station station) {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setId(station.getId());
        stationDTO.setNumber(station.getNumber());
        stationDTO.setLatitude(station.getLatitude());
        stationDTO.setLongitude(station.getLongitude());
        stationDTO.setPower(station.getPower());
        stationDTO.setWallMounted(station.isWallMounted());
        stationDTO.setPlugType(station.getPlugType() != null ? station.getPlugType().getDisplayName() : null);
        stationDTO.setPerHourPrice(station.getPerHourPrice());
        stationDTO.setAvailable(station.isAvailable());
        stationDTO.setPlaceDTO(station.getPlace() != null ? PlaceMapper.toDto(station.getPlace()) : null);
        stationDTO.setReservationsDTO(station.getReservations() != null ? station.getReservations()
                .stream()
                .map(ReservationMapper::toDto)
                .toList() : null);
        return stationDTO;
    }
}
