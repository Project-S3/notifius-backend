package ca.usherbrooke.notifius.backend.services.notificationsender;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.repositories.NotificationSenderRepository;
import ca.usherbrooke.notifius.backend.services.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public abstract class NotificationSender
{
    private NotificationSenderService notificationSenderService;

    abstract public void sendNotifications(Notification notification, User user);

    @Autowired
    public final void setNotificationSenderService(NotificationSenderService notificationSenderService)
    {
        this.notificationSenderService = notificationSenderService;
    }

    @PostConstruct
    public void subscribe()
    {
        notificationSenderService.addNotificationSenderSubscriber(this);
    }

    public NotificationSenderService getNotificationSenderService()
    {
        return notificationSenderService;
    }

    abstract public String getNotificationSenderId();
}
