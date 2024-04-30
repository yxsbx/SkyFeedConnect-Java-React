package com.Ada.SFCAuthenticator.dto;

/**
 * Data Transfer Object (DTO) representing user request data (username, email, password).
 */
public record UserRequestDTO(
        String username,
        String email,
        String password
) {
}
