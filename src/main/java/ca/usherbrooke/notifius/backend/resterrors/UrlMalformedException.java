/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UrlMalformedException extends ResponseStatusException
{
    public UrlMalformedException() {
        super(HttpStatus.BAD_REQUEST, "URL du Webhooks est invalide");
    }
}
