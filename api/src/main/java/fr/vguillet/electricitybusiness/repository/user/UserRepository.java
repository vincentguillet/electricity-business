package fr.vguillet.electricitybusiness.repository.user;

import fr.vguillet.electricitybusiness.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
