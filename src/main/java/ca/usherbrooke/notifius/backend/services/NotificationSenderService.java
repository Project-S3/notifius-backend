package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.NotificationSenderEntity;
import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.notificationsenders.NotificationSender;
import ca.usherbrooke.notifius.backend.repositories.NotificationSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationSenderService
{
    private final List<NotificationSender> notificationSenderSubscribers = new ArrayList<>();

    @Autowired
    private NotificationSenderRepository notificationSenderRepository;

    public void addNotificationSenderSubscriber(NotificationSender subscriber)
    {
        notificationSenderSubscribers.add(subscriber);

        notificationSenderRepository.save(new NotificationSenderEntity().withId(subscriber.getNotificationSenderId()));
        subscriber.getNotificationSenderId();
    }

    public Set<String> getAll()
    {
        return notificationSenderRepository.findAll().stream()
                                           .map(NotificationSenderEntity::getId)
                                           .collect(Collectors.toSet());
    }

    @Async
    public void sendNotifications(Notification notification, User user)
    {
        if (user.getEnableServices().contains(notification.getService())) {
            notificationSenderSubscribers.forEach(o -> o.sendNotifications(notification, user));
        }
    }


}
