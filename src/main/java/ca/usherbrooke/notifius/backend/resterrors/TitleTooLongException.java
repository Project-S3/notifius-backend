/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TitleTooLongException extends ResponseStatusException
{
    public TitleTooLongException()
    {
        super(HttpStatus.PRECONDITION_FAILED, "Le titre est trop long.");
    }
}
