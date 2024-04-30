package com.Ada.SFCAuthenticator.repository;

import com.Ada.SFCAuthenticator.model.UserVerifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for UserVerifier entities.
 */
public interface UserVerifierRepository extends JpaRepository<UserVerifier, Long> {
    Optional<UserVerifier> findByIdentifier(UUID uuid);
}
