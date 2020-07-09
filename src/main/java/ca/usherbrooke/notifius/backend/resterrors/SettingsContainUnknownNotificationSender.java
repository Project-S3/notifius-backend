/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.resterrors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SettingsContainUnknownNotificationSender extends ResponseStatusException
{

    public SettingsContainUnknownNotificationSender()
    {
        super(HttpStatus.PRECONDITION_FAILED, "Il y a un type de notification sender qui n'existe pas dans les settingds");
    }

}
