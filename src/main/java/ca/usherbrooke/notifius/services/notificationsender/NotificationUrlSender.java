package ca.usherbrooke.notifius.services.notificationsender;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import org.springframework.stereotype.Component;

@Component
public class NotificationUrlSender extends NotificationSender
{
    @Override
    void sendNotifications(Notification notification, User user)
    {

    }
}
