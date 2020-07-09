/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DiscordMalformedException extends ResponseStatusException
{
    public DiscordMalformedException() {
        super(HttpStatus.PRECONDITION_FAILED, "URL du discord Webhooks est invalide");
    }
}
