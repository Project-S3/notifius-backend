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
public class DiscordNotificationSender extends NotificationSender
{
    @Autowired
    private HttpService httpService;

    @Value("${notifius.discord.username}")
    private String discordUsername;
    @Value("${notifius.discord.avatar-url}")
    private String discordAvatarId;

    @Override
    void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getDiscordWebhookEnable()) {
            JSONObject notif = new JSONObject();
            try {
                notif.put("username", discordUsername);
                notif.put("avatar_url", discordAvatarId);
                notif.put("content", String.format(">>> **%s**\\n%s", notification.getTitle(), notification.getContent()));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            httpService.postJson(user.getDiscordWebhookUrl(), notif);
         }
    }
}
