/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.notificationsenders;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.SmsService;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationSender extends NotificationSender
{
    public static final String SMS_SENDER_ID = "SMS_SENDER";

    @Autowired
    private SmsService smsService;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        userNotificationSenderService.getValueIfExists(user.getId(), this.getNotificationSenderId())
                                     .ifPresent(phoneNumber -> smsService.sendSMS(phoneNumber,
                                                                                  String.format(
                                                                                          "%s\n\n%s\n\nEnvoyé par Notifius",
                                                                                          notification.getTitle(),
                                                                                          notification.getContent())));
    }

    @Override
    public String getNotificationSenderId()
    {
        return SMS_SENDER_ID;
    }

    @Override
    public String getNotificationSenderDisplayName()
    {
        return "Texto";
    }

    @Override
    public void validValueThrow(String value)
    {

    }


}
