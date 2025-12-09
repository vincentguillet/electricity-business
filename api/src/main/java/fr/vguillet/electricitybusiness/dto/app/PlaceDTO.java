package fr.vguillet.electricitybusiness.dto.app;

import fr.vguillet.electricitybusiness.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {
    private Long id;
    private String label;
    private String instructions;
    private UserDTO owner;
    private AddressDTO addressDTO;
}
