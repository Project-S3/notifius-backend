package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TitleEmptyException extends ResponseStatusException
{
    public TitleEmptyException()
    {
        super(HttpStatus.PRECONDITION_REQUIRED, "Vous devez fournir un titre.");
    }
}
