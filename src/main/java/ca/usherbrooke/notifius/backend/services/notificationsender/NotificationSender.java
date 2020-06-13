package ca.usherbrooke.notifius.backend.services.notificationsender;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public abstract class NotificationSender
{
    private NotificationSenderService notificationSenderService;

    abstract void sendNotifications(Notification notification, User user);

    @Autowired
    public final void setNotificationSenderService(NotificationSenderService notificationSenderService)
    {
        this.notificationSenderService = notificationSenderService;
    }

    @PostConstruct
    public void subscribe() {
        notificationSenderService.addNotificationSenderSubscriber(this);
    }
}
