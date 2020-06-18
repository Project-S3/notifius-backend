package ca.usherbrooke.notifius.backend.translators;

import ca.usherbrooke.notifius.backend.entities.NotificationEntity;
import ca.usherbrooke.notifius.backend.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationToEntityTranslator
{
    @Autowired
    ServiceToEntityTranslator serviceToEntityTranslator;

    public NotificationEntity toEntity(Notification notification)
    {
        return new NotificationEntity().withTitle(notification.getTitle())
                                       .withContent(notification.getContent())
                                       .withDate(notification.getDate())
                                       .withService(serviceToEntityTranslator.toEntity(notification.getService()));
    }

    public Notification toModel(NotificationEntity notificationEntity)
    {
        return new Notification().withTitle(notificationEntity.getTitle())
                                 .withContent(notificationEntity.getContent())
                                 .withDate(notificationEntity.getDate())
                                 .withService(serviceToEntityTranslator.toModel(notificationEntity.getService()));

    }
}
