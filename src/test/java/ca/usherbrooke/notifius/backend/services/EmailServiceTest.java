/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class EmailServiceTest
{
    public static final String EMAIL = "test@test.test";
    public static final String DISPLAY_NAME = "display_name";
    public static final String TO = "to";
    public static final String SUBJECT = "subject";
    public static final String TEXT = "test test test";

    @Spy
    private JavaMailSender emailSenderSpy;

    @InjectMocks
    private EmailService emailService;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> messageCaptor;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        emailService.setNotifiusEmail(EMAIL);
        emailService.setNotifiusEmailDisplayName(DISPLAY_NAME);
    }

    @Test
    public void sendEmailOK()
    {
        emailService.sendEmail(TO, SUBJECT, TEXT);

        verify(emailSenderSpy).send(messageCaptor.capture());
        SimpleMailMessage message = messageCaptor.getValue();

        assertEquals(String.format("%s <%s>", DISPLAY_NAME, EMAIL), message.getFrom());
        assertEquals(1, requireNonNull(message.getTo()).length);
        assertEquals(TO, message.getTo()[0]);
        assertEquals(SUBJECT, message.getSubject());
        assertEquals(TEXT, message.getText());
    }

    @Test
    public void sendEmailError()
    {
        doThrow(MailSendException.class).when(emailSenderSpy).send(any(SimpleMailMessage.class));
        emailService.sendEmail(TO, SUBJECT, TEXT);
    }
}