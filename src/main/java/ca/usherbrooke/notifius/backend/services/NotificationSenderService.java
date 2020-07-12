/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.NotificationSenderEntity;
import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.NotificationSenderModel;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.notificationsenders.NotificationSender;
import ca.usherbrooke.notifius.backend.repositories.NotificationSenderRepository;
import ca.usherbrooke.notifius.backend.translators.NotificationSenderModelToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationSenderService
{
    private List<NotificationSender> notificationSenderSubscribers = new ArrayList<>();

    @Autowired
    private NotificationSenderRepository notificationSenderRepository;
    @Autowired
    private NotificationSenderModelToEntityTranslator notificationSenderModelToEntityTranslator;

    public void addNotificationSenderSubscriber(NotificationSender subscriber)
    {
        if (subscriber == null) return;

        notificationSenderRepository.save(new NotificationSenderEntity()
                                                  .withId(subscriber.getNotificationSenderId())
                                                  .withDisplayName(subscriber.getNotificationSenderDisplayName()));
        notificationSenderSubscribers.add(subscriber);
    }

    public List<NotificationSenderModel> getAllNotificationSenderModel()
    {
        return notificationSenderRepository.findAll()
                                           .stream()
                                           .map(notificationSenderModelToEntityTranslator::toModel)
                                           .collect(Collectors.toList());
    }

    @Async
    public void sendNotifications(Notification notification, User user)
    {
        if (notification == null || user == null) return;

        if (user.getEnableServices().contains(notification.getService())) {
            notificationSenderSubscribers.forEach(o -> o.sendNotifications(notification, user));
        }
    }

    public List<NotificationSender> getNotificationSenderSubscribers()
    {
        return notificationSenderSubscribers;
    }

    public void setNotificationSenderSubscribers(List<NotificationSender> notificationSenderSubscribers)
    {
        this.notificationSenderSubscribers = notificationSenderSubscribers;
    }
}
