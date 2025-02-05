package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public class NotificationSenderNotFoundException extends ResponseStatusException
{
    public NotificationSenderNotFoundException()
    {
        super(HttpStatus.BAD_REQUEST, "Le type d'envoie n'existe pas.");
    }

    public NotificationSenderNotFoundException(String notificationSender)
    {
        super(HttpStatus.BAD_REQUEST, String.format("Le type d'envoie %s n'existe pas.", notificationSender));
    }
}

