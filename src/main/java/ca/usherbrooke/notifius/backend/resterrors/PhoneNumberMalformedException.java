/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PhoneNumberMalformedException extends ResponseStatusException
{
    public PhoneNumberMalformedException()
    {
        super(HttpStatus.PRECONDITION_FAILED, "Le numéro de téléphone est invalide");
    }
}
