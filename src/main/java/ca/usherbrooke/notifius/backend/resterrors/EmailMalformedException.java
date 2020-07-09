/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailMalformedException extends ResponseStatusException
{
    public EmailMalformedException() {
        super(HttpStatus.PRECONDITION_FAILED, "Le email est invalid");
    }
}