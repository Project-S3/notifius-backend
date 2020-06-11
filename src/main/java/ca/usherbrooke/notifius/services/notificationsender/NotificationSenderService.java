package ca.usherbrooke.notifius.services.notificationsender;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationSenderService
{
    private final List<NotificationSender> notificationSenderSubscribers = new ArrayList<>();

    public void addNotificationSenderSubscriber(NotificationSender subscriber)
    {
        notificationSenderSubscribers.add(subscriber);
    }

    // TODO METTRE DES LOGS
    public void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getEnableServices().contains(notification.getService())) {
            notificationSenderSubscribers.forEach(o -> o.sendNotifications(notification, user));
        }
    }
}
