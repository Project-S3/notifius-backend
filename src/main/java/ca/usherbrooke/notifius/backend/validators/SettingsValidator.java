/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.validators;

import ca.usherbrooke.notifius.backend.models.NotificationSenderModel;
import ca.usherbrooke.notifius.backend.models.Settings;
import ca.usherbrooke.notifius.backend.resterrors.SettingsContainUnknownNotificationSender;
import ca.usherbrooke.notifius.backend.services.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SettingsValidator
{
    @Autowired
    private NotificationSenderService notificationSenderService;

    // todo user validator
    public void validNotificationThrowIfNotValid(Settings settings)
    {
        if (!notificationSenderService.getAll().stream().map(NotificationSenderModel::getId)
                                      .collect(Collectors.toSet())
                                      .containsAll(settings.getNotificationSenders().keySet()))
            throw new SettingsContainUnknownNotificationSender();


    }
}
