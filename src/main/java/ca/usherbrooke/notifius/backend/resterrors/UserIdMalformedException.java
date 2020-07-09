/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserIdMalformedException extends ResponseStatusException
{
    public UserIdMalformedException()
    {
        super(HttpStatus.BAD_REQUEST, "Un cip est 4 lettres minuscules et 4 chiffres");
    }
}
