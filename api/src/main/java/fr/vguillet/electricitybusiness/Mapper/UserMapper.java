package fr.vguillet.electricitybusiness.Mapper;

import fr.vguillet.electricitybusiness.dto.user.UserDTO;
import fr.vguillet.electricitybusiness.model.user.Role;
import fr.vguillet.electricitybusiness.model.user.User;

public class UserMapper {

    public static User fromDto(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.valueOf(userDTO.getRole() != null ? userDTO.getRole() : "USER"));
        user.setOnVacation(userDTO.isOnVacation());
        user.setBanned(userDTO.isBanned());
        return user;
    }

    public static UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole() != null ? user.getRole().getDisplayName() : null);
        userDTO.setOnVacation(user.isOnVacation());
        userDTO.setBanned(user.isBanned());
        return userDTO;
    }
}
