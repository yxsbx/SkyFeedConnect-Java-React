package com.Ada.SFCAuthenticator.config;

import com.Ada.SFCAuthenticator.dto.ExceptionMessageDTO;
import com.Ada.SFCAuthenticator.dto.VerifyMessage;
import com.Ada.SFCAuthenticator.model.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler to handle specific exceptions and provide appropriate responses.
 */
@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionMessageDTO> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(new ExceptionMessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ExceptionMessageDTO> handleInvalidPasswordException(InvalidPasswordException ex) {
        return ResponseEntity.status(400).body(new ExceptionMessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionMessageDTO> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return ResponseEntity.status(409).body(new ExceptionMessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(EmailExpiredException.class)
    public ResponseEntity<ExceptionMessageDTO> handleEmailExpiredException(EmailExpiredException ex) {
        return ResponseEntity.status(400).body(new ExceptionMessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(AccountAlreadyVerifiedOrWrongLinkException.class)
    public ResponseEntity<ExceptionMessageDTO> handleAccountAlreadyVerifiedOrWrongLinkException(AccountAlreadyVerifiedOrWrongLinkException ex) {
        return ResponseEntity.status(409).body(new ExceptionMessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionMessageDTO> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.status(401).body(new ExceptionMessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessageDTO> handleGenericException( Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionMessageDTO("Ocorreu um erro inesperado"+ex.getMessage()));
    }
}