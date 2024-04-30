package com.Ada.SFCAuthenticator.model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for invalid login or password.
 */
@ResponseStatus
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
    super("Invalid login or password");
  }
}
