package fr.vguillet.electricitybusiness.controller.auth;

import fr.vguillet.electricitybusiness.Mapper.user.UserMapper;
import fr.vguillet.electricitybusiness.dto.auth.AuthRequest;
import fr.vguillet.electricitybusiness.dto.auth.AuthResponse;
import fr.vguillet.electricitybusiness.dto.user.UserDTO;
import fr.vguillet.electricitybusiness.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDTO dto,
                                                 HttpServletResponse response) {
        log.info("Registering user: {}", dto);
        return authService.register(UserMapper.fromDto(dto))
                .map(authResponse -> createAuthResponse(authResponse, response))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request,
                                                               HttpServletResponse response) {
        log.info("Authenticating user: {}", request);
        return authService.authenticate(request)
                .map(authResponse -> createAuthResponse(authResponse, response))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        log.info("Logout cookie: {}", cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(HttpServletRequest request) {
        String username = request.getUserPrincipal() != null
                ? request.getUserPrincipal().getName()
                : null;

        log.info("Getting current user: {}", username);
        return authService.getCurrentUser(username)
                .map(user -> ResponseEntity.ok(UserMapper.toDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshPost(HttpServletRequest request,
                                                    HttpServletResponse response) {
        return handleRefresh(request, response);
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshGet(HttpServletRequest request,
                                                   HttpServletResponse response) {
        return handleRefresh(request, response);
    }

    private ResponseEntity<AuthResponse> handleRefresh(HttpServletRequest request,
                                                       HttpServletResponse response) {
        String refreshToken = null;

        if (request.getCookies() != null) {
            refreshToken = Arrays.stream(request.getCookies())
                    .filter(c -> "refreshToken".equals(c.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }

        if (refreshToken == null) {
            return ResponseEntity.noContent().build();
        }

        return authService.refresh(refreshToken)
                .map(authResponse -> createAuthResponse(authResponse, response))
                .orElse(ResponseEntity.noContent().build());
    }

    private ResponseEntity<AuthResponse> createAuthResponse(AuthResponse authResponse,
                                                            HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", authResponse.refreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(60 * 60 * 24 * 7) // 7 jours
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new AuthResponse(
                authResponse.username(),
                authResponse.accessToken(),
                null
        ));
    }
}