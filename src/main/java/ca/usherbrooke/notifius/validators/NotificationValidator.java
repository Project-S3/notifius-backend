package ca.usherbrooke.notifius.validators;

import ca.usherbrooke.notifius.models.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationValidator
{
    @Value("${notifius.request.max-string-length}")
    private Integer maxStringLength;

    public boolean validNotification(Notification notification)
    {
        return true;
    }
}
