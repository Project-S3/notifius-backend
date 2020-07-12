/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.restcontrollers;

import ca.usherbrooke.notifius.backend.models.NotificationSenderModel;
import ca.usherbrooke.notifius.backend.services.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification-senders")
public class NotificationSenderController
{
    @Autowired
    private NotificationSenderService notificationSenderService;

    @GetMapping("")
    public List<NotificationSenderModel> getAllNotificationSender()
    {
        return notificationSenderService.getAllNotificationSenderModel();
    }
}
