/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class UserNotFoundException extends ResponseStatusException
{
    public UserNotFoundException()
    {
        super(HttpStatus.BAD_REQUEST, "L'utilisateur n'existe pas.");
    }

    public UserNotFoundException(String userId)
    {
        super(HttpStatus.BAD_REQUEST, String.format("L'utilisateur %s n'existe pas.", userId));
    }
}