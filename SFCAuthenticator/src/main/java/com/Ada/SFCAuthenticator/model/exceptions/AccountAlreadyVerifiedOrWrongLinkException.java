package com.Ada.SFCAuthenticator.model.exceptions;

/**
 * Exception class for account already verified or wrong link scenarios.
 */
public class AccountAlreadyVerifiedOrWrongLinkException extends RuntimeException {
    public AccountAlreadyVerifiedOrWrongLinkException() {
        super("Account already verified or wrong link");
    }
}
