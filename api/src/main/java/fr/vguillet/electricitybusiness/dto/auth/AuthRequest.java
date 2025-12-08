package fr.vguillet.electricitybusiness.dto.auth;

public record AuthRequest(
        String username,
        String password
) {
}