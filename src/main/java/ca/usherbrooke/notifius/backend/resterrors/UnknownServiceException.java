/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnknownServiceException extends ResponseStatusException
{
    public UnknownServiceException()
    {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
