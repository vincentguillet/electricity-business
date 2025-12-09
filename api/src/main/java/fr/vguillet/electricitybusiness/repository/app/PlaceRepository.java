package fr.vguillet.electricitybusiness.repository.app;

import fr.vguillet.electricitybusiness.model.app.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
