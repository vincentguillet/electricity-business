package fr.vguillet.electricitybusiness.Mapper.app;

import fr.vguillet.electricitybusiness.Mapper.user.UserMapper;
import fr.vguillet.electricitybusiness.dto.app.PlaceDTO;
import fr.vguillet.electricitybusiness.model.app.Place;

public class PlaceMapper {

    public static Place fromDto(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setId(placeDTO.getId());
        place.setLabel(placeDTO.getLabel());
        place.setInstructions(placeDTO.getInstructions());
        place.setOwner(placeDTO.getOwner() != null ? UserMapper.fromDto(placeDTO.getOwner()) : null);
        place.setAddress(placeDTO.getAddressDTO() != null ? AddressMapper.fromDto(placeDTO.getAddressDTO()) : null);
        return place;
    }

    public static PlaceDTO toDto(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setLabel(place.getLabel());
        placeDTO.setInstructions(place.getInstructions());
        placeDTO.setOwner(place.getOwner() != null ? UserMapper.toDto(place.getOwner()) : null);
        placeDTO.setAddressDTO(place.getAddress() != null ? AddressMapper.toDto(place.getAddress()) : null);
        return placeDTO;
    }
}
