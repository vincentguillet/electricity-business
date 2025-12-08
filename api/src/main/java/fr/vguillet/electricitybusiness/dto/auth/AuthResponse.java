package fr.vguillet.electricitybusiness.dto.auth;

public record AuthResponse(
        String username,
        String accessToken,
        String refreshToken
) {}