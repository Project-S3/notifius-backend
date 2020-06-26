/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.notificationsenders;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.HttpService;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
                                         try {
                                             notif.put("title", notification.getTitle());
                                             notif.put("content", notification.getContent());
                                             notif.put("date", notification.getDate());
                                             notif.put("service", notification.getService());

                                         } catch (JSONException e) {
                                             e.printStackTrace();
                                         }
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
        return "URL HTTP personalisée";
    }
}
