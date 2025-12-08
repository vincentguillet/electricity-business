package fr.vguillet.electricitybusiness.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;
    private boolean onVacation;
    private boolean isBanned;
}
