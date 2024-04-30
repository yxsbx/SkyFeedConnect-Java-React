package com.Ada.SFCAuthenticator.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * This service class handles sending emails using JavaMailSender.
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;

    /**
     * Sends an email using the configured JavaMailSender.
     * @param destiny The recipient email address.
     * @param subject The email subject.
     * @param body The email body content.
     * @param headers The HTTP headers.
     * @throws MessagingException If an error occurs during email sending.
     */
    public void sendEmail(String destiny, String subject, String body, HttpHeaders headers) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        helper.setFrom(sender);
        helper.setTo(destiny);
        helper.setSubject(subject);
        helper.setText(body, true);

        javaMailSender.send(message);
        System.err.println("Email de verificação enviado");
    }
}
