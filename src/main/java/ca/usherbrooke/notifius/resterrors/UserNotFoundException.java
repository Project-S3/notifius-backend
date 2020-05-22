package ca.usherbrooke.notifius.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class UserNotFoundException extends ResponseStatusException
{
    public UserNotFoundException()
    {
        super(HttpStatus.BAD_REQUEST, "L'utilisateur n'existe pas.");
    }
}