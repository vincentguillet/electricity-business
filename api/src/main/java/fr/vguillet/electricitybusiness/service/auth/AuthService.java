package fr.vguillet.electricitybusiness.service.auth;

import fr.vguillet.electricitybusiness.dto.auth.AuthRequest;
import fr.vguillet.electricitybusiness.dto.auth.AuthResponse;
import fr.vguillet.electricitybusiness.model.security.Token;
import fr.vguillet.electricitybusiness.model.user.User;
import fr.vguillet.electricitybusiness.repository.security.TokenRepository;
import fr.vguillet.electricitybusiness.repository.user.UserRepository;
import fr.vguillet.electricitybusiness.service.security.JpaUserDetailsService;
import fr.vguillet.electricitybusiness.service.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JpaUserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<AuthResponse> register(User user) {
        String username = user.getUsername();

        if (userRepository.findByUsername(username).isPresent()) {
            System.err.println("User already exists: " + username);
            return Optional.empty();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsService.registerNewUser(user);

        return Optional.of(new AuthResponse(username, null, null));
    }

    @Transactional
    public Optional<AuthResponse> authenticate(AuthRequest request) {
        String accessToken = jwtService.generateToken(request.username());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + userDetails.getUsername()));

        Token token = user.getToken() != null
                ? tokenRepository.findById(user.getToken().getId()).orElse(null)
                : null;

        if (token == null || isRefreshTokenExpired(token)) {
            System.out.println("[Authenticate] Refresh token absent ou expiré pour user: " + user.getUsername());
            token = generateNewRefreshToken(user);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        return Optional.of(new AuthResponse(
                user.getUsername(),
                accessToken,
                token.getValue()
        ));
    }

    @Transactional
    public Optional<User> getCurrentUser(String username) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    if (user.getToken() != null && isRefreshTokenExpired(user.getToken())) {
                        System.out.println("[AuthService] Refresh token expired for user: " + user.getUsername());
                        user.setToken(null);
                        userRepository.save(user);
                    }
                    return user;
                });
    }

    @Transactional
    public Optional<AuthResponse> refresh(String refreshTokenValue) {
        Token savedToken = tokenRepository.findByValue(refreshTokenValue)
                .orElseThrow(() -> new RuntimeException("Refresh token not found: " + refreshTokenValue));

        User user = savedToken.getUser();

        if (isRefreshTokenExpired(savedToken)) {
            return Optional.empty();
        }

        String newAccessToken = jwtService.generateToken(user.getUsername());
        return Optional.of(new AuthResponse(
                user.getUsername(),
                newAccessToken,
                refreshTokenValue
        ));
    }

    private Token generateNewRefreshToken(User user) {
        if (user.getToken() != null && tokenRepository.findById(user.getToken().getId()).isPresent()) {
            System.out.println("[AuthService] Deleting existing refresh token for user: " + user.getUsername());
            tokenRepository.delete(user.getToken());
            user.setToken(null);
            userRepository.save(user);
            tokenRepository.flush();
        }

        Token newToken = new Token();
        newToken.setUser(user);
        newToken.setValue(jwtService.generateToken(user.getUsername()));
        newToken.setExpirationDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)));  // 7 days in milliseconds
        tokenRepository.save(newToken);
        user.setToken(newToken);
        userRepository.save(user);
        return newToken;
    }

    private boolean isRefreshTokenExpired(Token token) {
        return token.getExpirationDate().getTime() < System.currentTimeMillis();
    }
}