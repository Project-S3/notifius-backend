/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ContentTooLongException extends ResponseStatusException
{
    public ContentTooLongException()
    {
        super(HttpStatus.PRECONDITION_FAILED, "Le contenu est trop long.");
    }
}
