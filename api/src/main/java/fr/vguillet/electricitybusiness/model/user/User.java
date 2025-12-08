package fr.vguillet.electricitybusiness.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.vguillet.electricitybusiness.model.security.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 30, nullable = false)
    private String firstName;

    @NotBlank
    @Column(length = 30, nullable = false)
    private String lastName;

    @Column(length = 0)
    private String username;

    @Email
    @NotBlank
    @Column(length = 120, unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(length = 120, nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Token token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.USER.name()));
    }
}
