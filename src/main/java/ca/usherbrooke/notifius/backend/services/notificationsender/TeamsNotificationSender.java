package ca.usherbrooke.notifius.backend.services.notificationsender;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.HttpService;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class TeamsNotificationSender extends NotificationSender
{
    public static final String TEAMS_SENDER_ID = "TEAMS_SENDER";

    @Autowired
    private HttpService httpService;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        userNotificationSenderService.getValueIfExists(user.getId(), this.getNotificationSenderId())
                                     .ifPresent(teamsWebhookUrl -> {
                                         JSONObject notif = new JSONObject();
                                         try {
                                             notif.put("title", notification.getTitle());
                                             notif.put("text", notification.getContent());
                                         } catch (JSONException e) {
                                             e.printStackTrace();
                                         }
                                         httpService.postJson(teamsWebhookUrl, notif);
                                     });
    }

    @Override
    public String getNotificationSenderId()
    {
        return TEAMS_SENDER_ID;
    }
}
