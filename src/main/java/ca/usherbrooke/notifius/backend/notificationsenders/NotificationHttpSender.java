/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.notificationsenders;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.resterrors.UrlMalformedException;
import ca.usherbrooke.notifius.backend.services.HttpService;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotificationHttpSender extends NotificationSender
{
    public static final String HTTP_SENDER_ID = "HTTP_SENDER";

    @Autowired
    private HttpService httpService;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        userNotificationSenderService.getValueIfExists(user.getId(), this.getNotificationSenderId())
                                     .ifPresent(customUrl -> {
                                         JSONObject notif = new JSONObject();
                                         notif.put("title", notification.getTitle());
                                         notif.put("content", notification.getContent());
                                         notif.put("date", notification.getDate().toString());
                                         notif.put("service", notification.getService().toString());
                                         httpService.postJson(customUrl, notif);
                                     });
    }

    @Override
    public String getNotificationSenderId()
    {
        return HTTP_SENDER_ID;
    }

    @Override
    public String getNotificationSenderDisplayName()
    {
        return "Webhook personalisée";
    }

    @Override
    public void validValueThrow(String value)
    {
        UrlValidator urlValidator = new UrlValidator();

        if(!urlValidator.isValid(value)) {
            throw new UrlMalformedException();
        }
    }
}
