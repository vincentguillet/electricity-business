package fr.vguillet.electricitybusiness.repository.app;

import fr.vguillet.electricitybusiness.model.app.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
