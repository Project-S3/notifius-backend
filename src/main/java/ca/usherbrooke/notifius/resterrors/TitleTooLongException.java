package ca.usherbrooke.notifius.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TitleTooLongException extends ResponseStatusException
{
    public TitleTooLongException()
    {
        super(HttpStatus.PRECONDITION_FAILED, "Le titre est trop long.");
    }
}
