/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ContentEmptyException extends ResponseStatusException
{
    public ContentEmptyException()
    {
        super(HttpStatus.BAD_REQUEST, "Vous devez fournir un contenu.");
    }
}
