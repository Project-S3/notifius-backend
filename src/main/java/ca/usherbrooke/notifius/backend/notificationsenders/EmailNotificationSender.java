/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.notificationsenders;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.EmailService;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender extends NotificationSender
{
    public static final String EMAIL_SENDER_ID = "EMAIL_SENDER";

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        userNotificationSenderService.getValueIfExists(user.getId(), this.getNotificationSenderId())
                                     .ifPresent(email -> emailService.sendEmail(email,
                                                                                notification.getTitle(),
                                                                                notification.getContent()));
    }

    @Override
    public String getNotificationSenderId()
    {
        return EMAIL_SENDER_ID;
    }

    @Override
    public String getNotificationSenderDisplayName()
    {
        return "Courriel";
    }

    @Override
    public boolean validValue(String value)
    {
        return EmailValidator.getInstance().isValid(value);
    }
}
