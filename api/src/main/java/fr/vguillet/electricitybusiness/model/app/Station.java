package fr.vguillet.electricitybusiness.model.app;

import fr.vguillet.electricitybusiness.model.app.reservation.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private Integer number;

    // Coordinates (latitude, longitude)

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String latitude;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String longitude;

    private String instructions;

    private Integer power; // in kW

    private boolean wallMounted;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PlugType plugType;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private double perHourPrice;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "station")
    private List<Reservation> reservations;
}
