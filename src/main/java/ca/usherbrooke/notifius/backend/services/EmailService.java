/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String notifiusEmail;
    @Value("${notifius.email.display-Name}")
    private String notifiusEmailDisplayName;

    public void sendEmail(String to, String subject, String text)
    {
        int numberOfTry = 0;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(String.format("%s <%s>", notifiusEmailDisplayName, notifiusEmail));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            emailSender.send(message);
        } catch (MailSendException e) {
            logger.warn("Error when trying to send email to: {}", to);
        }
    }
}
