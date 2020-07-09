/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.validators;

import ca.usherbrooke.notifius.backend.models.Settings;
import ca.usherbrooke.notifius.backend.notificationsenders.NotificationSender;
import ca.usherbrooke.notifius.backend.resterrors.SettingsContainUnknownNotificationSender;
import ca.usherbrooke.notifius.backend.services.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettingsValidator
{
    @Autowired
    private NotificationSenderService notificationSenderService;

    public void validSettingsThrowIfNotValid(Settings settings)
    {
        List<NotificationSender> notificationSenders = notificationSenderService.getAll();
        if (!notificationSenders.stream()
                                .map(NotificationSender::getNotificationSenderId)
                                .collect(Collectors.toSet())
                                .containsAll(settings.getNotificationSenders().keySet())) {

            throw new SettingsContainUnknownNotificationSender();
        }

        notificationSenders.forEach(notificationSender -> {
            String value = settings.getNotificationSenders().get(notificationSender.getNotificationSenderId());
            if (value != null) {
                notificationSender.validValueThrow(value);
            }
        });

    }

    public void setNotificationSenderService(NotificationSenderService notificationSenderService)
    {
        this.notificationSenderService = notificationSenderService;
    }
}
