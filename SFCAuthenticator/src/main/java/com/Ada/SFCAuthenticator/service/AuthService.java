package com.Ada.SFCAuthenticator.service;

import com.Ada.SFCAuthenticator.model.exceptions.InvalidCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.Ada.SFCAuthenticator.dto.AccessDTO;
import com.Ada.SFCAuthenticator.dto.AuthenticationDTO;
import com.Ada.SFCAuthenticator.security.jwt.JwtUtils;

/**
 * This service class handles user authentication and JWT token generation.
 */
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    /**
     * Constructs the AuthService with the required dependencies.
     * @param authenticationManager The authentication manager.
     * @param jwtUtils The JWT utility for token operations.
     */
    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Handles user login and returns the JWT access token.
     * @param authDto The authentication DTO containing username and password.
     * @return An AccessDTO containing the JWT access token.
     * @throws InvalidCredentialsException If login credentials are invalid.
     */
    public AccessDTO login(AuthenticationDTO authDto) {

        try {
        UsernamePasswordAuthenticationToken userAuth =
              new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());

        Authentication authentication = authenticationManager.authenticate(userAuth);

        UserDetailsImpl userAuthDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthDetails);

        return new AccessDTO(token);

        } catch (BadCredentialsException e) {
        throw new InvalidCredentialsException();
        }
    }
}
