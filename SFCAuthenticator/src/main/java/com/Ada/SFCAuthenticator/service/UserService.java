package com.Ada.SFCAuthenticator.service;

import com.Ada.SFCAuthenticator.dto.AuthenticationDTO;
import com.Ada.SFCAuthenticator.dto.UserRequestDTO;
import com.Ada.SFCAuthenticator.dto.VerifyMessage;
import com.Ada.SFCAuthenticator.model.User;
import com.Ada.SFCAuthenticator.model.UserVerifier;
import com.Ada.SFCAuthenticator.model.enums.UserStatus;
import com.Ada.SFCAuthenticator.model.exceptions.*;
import com.Ada.SFCAuthenticator.repository.UserRepository;
import com.Ada.SFCAuthenticator.repository.UserVerifierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.time.Instant.now;

/**
 * This service class handles user-related operations like registration, deletion, and email verification.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final UserVerifierRepository verifierRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final Instant VERIFY_EMAIL_EXPIRATION = now().plusMillis(900000);

    @Value("${project.verify.dominio}")
    private String dominio;

    /**
     * Saves a new user in the system.
     * @param user The user request DTO containing user details.
     */
    public void saveNewUser(UserRequestDTO user) {
        if (userRepository.existsByLogin(user.email())) {
        throw new UserAlreadyRegisteredException(user.email());
        }

        User newUser = createUserFromDTO(user);
        UserVerifier verifier = createVerifier(newUser);
        sendWelcomeEmail(verifier);
    }

    /**
     * Creates a User entity from the user request DTO.
     * @param user The user request DTO.
     * @return The newly created User entity.
     */
    private User createUserFromDTO(UserRequestDTO user) {
        if (!isValidPassword(user.password())) {
        throw new InvalidPasswordException();
        }

        User newUser = new User(user);
        newUser.setPassword(encryptPassword(user.password()));
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * Checks if the password meets the required criteria.
     * @param password The password to validate.
     * @return True if the password is valid, false otherwise.
     */
    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    /**
     * Creates a verifier for the user for email verification.
     * @param user The user for whom the verifier is created.
     * @return The created UserVerifier entity.
     */
    private UserVerifier createVerifier(User user) {
        UserVerifier verifier = new UserVerifier();
        verifier.setUser(user);
        verifier.setIdentifier(UUID.randomUUID());
        verifier.setExpirationDate(VERIFY_EMAIL_EXPIRATION);
        verifierRepository.save(verifier);
        return verifier;
    }

    /**
     * Encrypts the user's password using the password encoder.
     * @param password The password to encrypt.
     * @return The encrypted password.
     */
    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Sends a welcome email to the user after successful registration.
     * @param user The user verifier for whom the email is sent.
     */
    private void sendWelcomeEmail(UserVerifier user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        String subject = "Bem-vindo(a) ao SkyFeedConnect";
        String activationLink = dominio + user.getIdentifier();
        String emailContent = String.format("""
            <html>
            <body style="font-family: Arial, sans-serif;">
                        
            <h2>Olá!</h2>
                        
            <br/>
                        
            <p>Obrigado por se cadastrar. Para ativar sua conta, clique no link abaixo:</p>
                        
            <a href="%s" style="padding: 10px 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 5px; margin-botton:10px;">Ativar Conta</a>
                        
            <br/>
            <br/>
                        
            <p>Se o botão acima não funcionar, copie e cole o seguinte URL em seu navegador:</p>
            <p>%s</p>
                        
            <br/>
                        
            <p>Obrigado,</p>
            <p>Equipe SkyFeed</p>
                        
            </body>
            </html>
            """, activationLink, activationLink);
        try {
            emailService.sendEmail(user.getUser().getEmail(), subject, emailContent, headers);
            logger.info("Email enviado para: {}", user.getUser().getEmail());
        } catch (Exception e) {
            logger.error("Falha ao enviar o email de verificação", e);
            throw new EmailSendingException();
        }
    }

    /**
     * Deletes a user based on authentication credentials.
     * @param access The authentication DTO containing username and password.
     */
    public void delete(AuthenticationDTO access) {
        User user = userRepository.findByLogin(access.username().trim().toLowerCase()).orElseThrow(
            () -> new UserNotFoundException(access.username())
        );

        if (passwordEncoder.matches(access.password(), user.getPassword())) {
          userRepository.delete(user);

        } else {
        throw new InvalidPasswordException();
        }
    }

    /**
     * Verifies a user's email based on the verification UUID.
     * @param uuid The UUID for email verification.
     * @return A VerifyMessage indicating the result of the verification process.
     */
    public VerifyMessage verifyUser(String uuid) {
        if (!Access(uuid)) {
        return new VerifyMessage("Email verificado com sucesso!");
        }

        UserVerifier user = verifierRepository.findByIdentifier(UUID.fromString(uuid))
            .orElseThrow(AccountAlreadyVerifiedOrWrongLinkException::new);

        if (user.getExpirationDate().isBefore(now())) {
        user.setIdentifier(UUID.randomUUID());
        user.setExpirationDate(VERIFY_EMAIL_EXPIRATION);
        resendVerificationEmail(user);
        return new VerifyMessage("Link expirado. Enviamos um novo link de verificação para seu email.");
        }

        User userToVerify = user.getUser();
        userToVerify.setStatus(UserStatus.ACTIVE);

        verifierRepository.delete(user);

        return new VerifyMessage("Email verificado com sucesso!");
    }

    String uuid2 = "";

    /**
     * Checks access based on UUID to prevent multiple verifications.
     * @param uuid The UUID for verification.
     * @return True if access is granted, false otherwise.
     */
    private boolean Access(String uuid) {
        if (uuid2.equals(uuid)) {
        return false;
        } else {
        uuid2 = uuid;
        return true;
        }
    }

    /**
     * Resends the verification email to the user with an updated verification link.
     * @param user The user verifier for whom the email is resent.
     */
    private void resendVerificationEmail(UserVerifier user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        String subject = "Link de Verificação Atualizado";
        String activationLink = dominio + user.getIdentifier();
        String emailContent = String.format("""
            <html>
            <body style="font-family: Arial, sans-serif;">

            <h2>Olá!</h2>
            
            <p>Reenviamos este e-mail porque o link de ativação anterior expirou.</p>
            <p>Clique no link abaixo para ativar sua conta:</p>
            
            <a href="%s" style="padding: 10px 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 5px;">Ativar Conta</a>
            
            <p>Se o botão acima não funcionar, copie e cole o seguinte URL em seu navegador:</p>
            <p>%s</p>
            
            <p>Este link expirará em 15 minutos.</p>
            
            <p>Obrigado,</p>
            <p>Sua Equipe</p>
            
            </body>
            </html>
            """, activationLink, activationLink);
        try {
            emailService.sendEmail(user.getUser().getEmail(), subject, emailContent, headers);
            logger.info("Email enviado para: {}", user.getUser().getEmail());
        } catch (Exception e) {
            logger.error("Falha ao enviar o email de verificação", e);
            throw new EmailSendingException();
        }
    }
}
