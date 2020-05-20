package ca.usherbrooke.notifius.translators;

import ca.usherbrooke.notifius.entities.NotificationEntity;
import ca.usherbrooke.notifius.models.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationToEntityTranslator
{
    public NotificationEntity toEntity(Notification notification)
    {
        return new NotificationEntity().withTitle(notification.getTitle())
                                       .withContent(notification.getTitle())
                                       .withDate(notification.getDate());
    }
}
