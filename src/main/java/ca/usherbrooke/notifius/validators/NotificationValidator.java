package ca.usherbrooke.notifius.validators;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.resterrors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;


@Service
public class NotificationValidator
{
    @Value("${notifius.request.max-string-length}")
    private Integer maxStringLength;

    public boolean validNotification(Notification notification)
    {
        if(notification.getContent().length() > maxStringLength)
            throw new ContentTooLongException();

        if(notification.getContent().isEmpty())
            throw new ContentEmptyException();

        if(notification.getTitle().length() > maxStringLength)
            throw new TitleTooLongException();

        if(notification.getContent().isEmpty())
            throw new TitleEmptyException();

        if(notification.getDate().compareTo(new Date()) > 0 )
            throw new DateInvalidException();

        return true;
    }
}
