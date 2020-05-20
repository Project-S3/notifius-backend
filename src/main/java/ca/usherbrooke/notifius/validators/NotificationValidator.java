package ca.usherbrooke.notifius.validators;

import ca.usherbrooke.notifius.models.Notification;
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
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le contenu est trop long.");

        if(notification.getContent().isEmpty())
            throw new ResponseStatusException(HttpStatus.PRECONDITION_REQUIRED, "Vous devez fournir un contenu.");

        if(notification.getTitle().length() > maxStringLength)
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le titre est trop long.");

        if(notification.getContent().isEmpty())
            throw new ResponseStatusException(HttpStatus.PRECONDITION_REQUIRED, "Vous devez fournir un titre.");

        if(notification.getDate().compareTo(new Date()) > 0 )
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "La date est invalide.");

        return true;
    }
}
