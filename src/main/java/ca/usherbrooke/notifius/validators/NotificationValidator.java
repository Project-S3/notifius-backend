package ca.usherbrooke.notifius.validators;

import ca.usherbrooke.notifius.models.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationValidator
{
    @Value("${notifius.request.max-string-length}")
    private Integer maxStringLength;

    public boolean validNotification(Notification notification)
    {
        if(notification.getContent().length() > maxStringLength) return false;
        if(notification.getTitle().length() > maxStringLength) return false;
        if(notification.getDate().compareTo(new Date()) > 0 ) return false;
        return true;
    }
}
