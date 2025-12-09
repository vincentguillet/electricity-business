package fr.vguillet.electricitybusiness.model.app;

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
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 50, nullable = false)
    private String label;

    @NotBlank
    private String instructions;

    @OneToOne
    private User owner;

    @ManyToOne
    private Address address;
}
