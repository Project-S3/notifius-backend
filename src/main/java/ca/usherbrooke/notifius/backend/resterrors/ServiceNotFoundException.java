/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ServiceNotFoundException extends ResponseStatusException
{
    public ServiceNotFoundException(String service)
    {
        super(HttpStatus.PRECONDITION_FAILED, String.format("Service %s not found", service));
    }
}
