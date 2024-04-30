package com.Ada.SFCAuthenticator.security.jwt;

import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.Ada.SFCAuthenticator.service.UserDetailsImpl;

/**
 * This class provides utility methods for JWT token generation, validation, and extraction.
 */
@Component
public class JwtUtils {

    @Value("${project.jwtSecret}")
    private String jwtSecret;

    @Value("${project.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Generates a JWT token from UserDetailsImpl.
     * @param userDetails The UserDetailsImpl object.
     * @return The generated JWT token.
     */
    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetails) {
        return Jwts.builder()
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(getSigningKey())
            .compact();
    }

    /**
     * Extracts the username from a JWT token.
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String extractUsernameFromJwtToken(String token) {
        return extractClaimFromJwtToken(token, Claims::getSubject);
    }

    /**
     * Validates a JWT token.
     * @param token The JWT token to be validated.
     * @return True if the token is valid, otherwise false.
     */
    public boolean validateJwtToken(String token) {
        try {
        Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
        return true;
        } catch (ExpiredJwtException e) {
        throw new RuntimeException("Token expirado", e);
        } catch (UnsupportedJwtException e) {
        throw new RuntimeException("Token não suportado", e);
        } catch (IllegalArgumentException e) {
        throw new RuntimeException("Token vazio", e);
        } catch (MalformedJwtException e) {
        throw new RuntimeException("Token inválido", e);
        }
    }

    /**
     * Extracts a specific claim from a JWT token.
     * @param token The JWT token.
     * @param claimsResolver The claims resolver function.
     * @param <T> The type of claim.
     * @return The extracted claim.
     */
    private <T> T extractClaimFromJwtToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    /**
     * Generates the signing key for JWT token.
     * @return The secret key used for signing JWT token.
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}

