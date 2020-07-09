/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.notificationsenders;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.HttpService;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlackWebhookSender extends NotificationSender
{
    public static final String SLACK_SENDER_ID = "SLACK_SENDER";

    @Autowired
    private HttpService httpService;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        userNotificationSenderService.getValueIfExists(user.getId(), this.getNotificationSenderId())
                                     .ifPresent(slackWebhookUrl -> {
                                         JSONObject notif = new JSONObject();
                                         notif.put("text", String.format("*%s*\n%s",
                                                                         notification.getTitle(),
                                                                         notification.getContent()));
                                         httpService.postJson(slackWebhookUrl, notif);
                                     });
    }

    @Override
    public String getNotificationSenderId()
    {
        return SLACK_SENDER_ID;
    }

    @Override
    public String getNotificationSenderDisplayName()
    {
        return "Slack";
    }

    @Override
    public void validValueThrow(String value)
    {

    }
}
