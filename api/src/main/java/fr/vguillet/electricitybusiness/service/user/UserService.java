package fr.vguillet.electricitybusiness.service.user;

import fr.vguillet.electricitybusiness.model.user.User;
import fr.vguillet.electricitybusiness.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void saveUser(User user) {
        if (user.getId() == null) {
            userRepository.save(user);
        }
    }

    @Transactional
    public Optional<User> updateUser(User user) {
        return userRepository.findById(user.getId()).map(existingUser -> {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setOnVacation(user.isOnVacation());
            existingUser.setBanned(user.isBanned());
            existingUser.setReservations(user.getReservations());
            return userRepository.save(existingUser);
        });
    }

    @Transactional
    public Optional<User> deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
        return user;
    }
}
