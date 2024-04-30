package com.Ada.SFCAuthenticator.model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for user already registered scenarios.
 */
@ResponseStatus
public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String description) {
        super("Usuário com email %s já está registrado.".formatted(description));
    }
}
