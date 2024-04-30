package com.Ada.SFCAuthenticator.model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for invalid password.
 */
@ResponseStatus
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Senha inválida. Tente novamente.");
    }
}
