package fr.vguillet.electricitybusiness.dto.app;

import fr.vguillet.electricitybusiness.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String street;
    private String city;
    private String zipCode;
    private List<UserDTO> usersDTO;
}
