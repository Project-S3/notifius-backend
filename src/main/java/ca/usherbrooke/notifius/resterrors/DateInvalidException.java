package ca.usherbrooke.notifius.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DateInvalidException extends ResponseStatusException
{
    public DateInvalidException()
    {
        super(HttpStatus.PRECONDITION_FAILED, "La date est invalide.");
    }
}
