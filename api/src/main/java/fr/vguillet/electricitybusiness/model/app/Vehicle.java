package fr.vguillet.electricitybusiness.model.app;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fr.vguillet.electricitybusiness.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String brand;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String model;

    @NotBlank
    @Column(nullable = false)
    private String year;

    @NotBlank
    @NotNull
    private String color;

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String licensePlate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PlugType plugType;

    private Integer batteryCapacity; // in kWh

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonBackReference
    private User owner;
}
