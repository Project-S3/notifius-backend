package ca.usherbrooke.notifius.validators;

import ca.usherbrooke.notifius.models.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationValidator
{
    public boolean validNotification(Notification notification)
    {
        return true;
    }
}
