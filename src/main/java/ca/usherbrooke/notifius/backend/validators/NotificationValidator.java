package ca.usherbrooke.notifius.backend.validators;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.resterrors.*;
import ca.usherbrooke.notifius.resterrors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationValidator
{
    @Value("${notifius.request.max-string-length}")
    private Integer maxStringLength;

    public void validNotificationThrowIfNotValid(Notification notification)
    {
        if (notification.getContent().length() > maxStringLength)
            throw new ContentTooLongException();

        if (notification.getContent().isEmpty())
            throw new ContentEmptyException();

        if (notification.getTitle().length() > maxStringLength)
            throw new TitleTooLongException();

        if (notification.getTitle().isEmpty())
            throw new TitleEmptyException();

        if (notification.getDate().compareTo(new Date()) > 0)
            throw new DateInvalidException();

        // todo service
    }
}
