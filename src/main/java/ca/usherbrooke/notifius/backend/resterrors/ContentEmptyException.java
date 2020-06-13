package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ContentEmptyException extends ResponseStatusException
{
    public ContentEmptyException()
    {
        super(HttpStatus.PRECONDITION_REQUIRED, "Vous devez fournir un contenu.");
    }
}
