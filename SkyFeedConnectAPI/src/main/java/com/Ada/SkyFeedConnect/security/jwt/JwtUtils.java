package com.Ada.SkyFeedConnect.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.function.Function;

/**
 * Utility class for handling JWT operations.
 */
@Component
public class JwtUtils {

    @Value("${project.jwtSecret}")
    private String jwtSecret;

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username extracted from the token.
     */
    public String extractUsernameFromJwtToken(String token) {
        return extractClaimFromJwtToken(token, Claims::getSubject);
    }

    /**
     * Validates a JWT token.
     *
     * @param token The JWT token to validate.
     * @return True if the token is valid, false otherwise.
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

    private <T> T extractClaimFromJwtToken(String token, Function<Claims, T> claimsResolver) {

        Claims claims = Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
