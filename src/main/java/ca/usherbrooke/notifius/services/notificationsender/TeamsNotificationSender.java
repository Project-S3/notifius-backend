package ca.usherbrooke.notifius.services.notificationsender;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.services.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class TeamsNotificationSender extends NotificationSender
{
    @Autowired
    private HttpService httpService;

    @Override
    void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getTeamsWebhookEnable()) {
            JSONObject notif = new JSONObject();
            try {
                notif.put("title", notification.getTitle());
                notif.put("text", notification.getContent());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            httpService.postJson(user.getTeamsWebhookUrl(), notif);
         }
    }
}