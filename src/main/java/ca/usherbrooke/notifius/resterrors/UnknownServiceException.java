package ca.usherbrooke.notifius.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnknownServiceException extends ResponseStatusException
{
    public UnknownServiceException()
    {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
