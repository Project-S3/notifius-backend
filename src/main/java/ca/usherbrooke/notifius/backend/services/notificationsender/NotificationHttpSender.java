package ca.usherbrooke.notifius.backend.services.notificationsender;

import ca.usherbrooke.notifius.backend.services.HttpService;
import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class NotificationHttpSender extends NotificationSender
{
    @Autowired
    private HttpService httpService;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        String attribute = userNotificationSenderService.getAttributeIfExists(user.getId(),this.getNotificationSenderId());
        if (attribute != null)
        {
            JSONObject notif = new JSONObject();
            try {
                notif.put("title", notification.getTitle());
                notif.put("content", notification.getContent());
                notif.put("date", notification.getDate());
                notif.put("service", notification.getService());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            httpService.postJson(attribute, notif);
        }
    }

    @Override
    public String getNotificationSenderId()
    {
        return "HTTP_SENDER";
    }
}
