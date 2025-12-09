package fr.vguillet.electricitybusiness.Mapper.app;

import fr.vguillet.electricitybusiness.Mapper.user.UserMapper;
import fr.vguillet.electricitybusiness.dto.app.AddressDTO;
import fr.vguillet.electricitybusiness.model.app.Address;

public class AddressMapper {

    public static Address fromDto(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setZipCode(addressDTO.getZipCode());
        address.setUsers(addressDTO.getUsersDTO() != null ? addressDTO.getUsersDTO()
                .stream()
                .map(UserMapper::fromDto)
                .toList() : null);
        return address;
    }

    public static AddressDTO toDto(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setUsersDTO(address.getUsers() != null ? address.getUsers()
                .stream()
                .map(UserMapper::toDto)
                .toList() : null);
        return addressDTO;
    }
}
