package com.Ada.SFCAuthenticator.model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for email sending errors.
 */
@ResponseStatus
public class EmailSendingException extends RuntimeException {
    public EmailSendingException() {
        super("Erro ao enviar email");
    }
}
