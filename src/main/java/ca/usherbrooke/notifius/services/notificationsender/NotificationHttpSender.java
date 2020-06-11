package ca.usherbrooke.notifius.services.notificationsender;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.services.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class NotificationHttpSender extends NotificationSender
{
    @Autowired
    private HttpService httpService;

    @Override
    void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getHttpServiceEnable()) {
            JSONObject notif = new JSONObject();
            try {
                notif.put("title", notification.getTitle());
                notif.put("content", notification.getContent());
                notif.put("date", notification.getDate());
                notif.put("service", notification.getService());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            httpService.postJson(user.getCustomUrl(), notif);
        }
    }
}
