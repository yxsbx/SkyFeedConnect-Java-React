package com.Ada.SFCAuthenticator.model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for user not found scenarios.
 */
@ResponseStatus
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String login) {
        super("Usuário '" + login + "' não encontrado!");
    }
}
