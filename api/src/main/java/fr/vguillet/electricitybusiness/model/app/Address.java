package fr.vguillet.electricitybusiness.model.app;

import fr.vguillet.electricitybusiness.model.user.User;
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
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String street;

    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    private String zipCode;

    @ManyToMany
    private List<User> users;
}
