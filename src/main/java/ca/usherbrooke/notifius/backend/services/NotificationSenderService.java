package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.notificationsender.NotificationSender;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    public void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getEnableServices().contains(notification.getService())) {
            notificationSenderSubscribers.forEach(o -> o.sendNotifications(notification, user));
        }
    }
}
