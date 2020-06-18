package ca.usherbrooke.notifius.backend.services.notificationsender;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SlackWebhookSender extends NotificationSender
{
    @Autowired
    private HttpService httpService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getSlackWebhookEnable()) {
            JSONObject notif = new JSONObject();
            try {
                notif.put("text", String.format("*%s*\n%s",notification.getTitle(), notification.getContent()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            httpService.postJson(user.getSlackWebhookUrl(), notif);
        }
    }

    @Override
    public String getNotificationSenderId()
    {
        return "SLACK_SENDER";
    }
}
