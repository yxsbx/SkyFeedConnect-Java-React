package com.Ada.SFCAuthenticator.model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for email verification link expiration.
 */
@ResponseStatus
public class EmailExpiredException extends RuntimeException {

    public EmailExpiredException() {
        super("Link de verificação expirado. Um novo link foi enviado para o seu e-mail.");
    }
}
