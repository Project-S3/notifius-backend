/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.repositories.NotificationRepository;
import ca.usherbrooke.notifius.backend.translators.NotificationToEntityTranslator;
import ca.usherbrooke.notifius.backend.translators.ServiceToEntityTranslator;
import ca.usherbrooke.notifius.backend.translators.UserToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class NotificationService
{
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationToEntityTranslator notificationToEntityTranslator;
    @Autowired
    private UserToEntityTranslator userToEntityTranslator;
    @Autowired
    private ServiceToEntityTranslator serviceToEntityTranslator;

    public void create(Notification notification, User user)
    {
        if (notification == null || user == null) return;

        Notification notif = notification;
        notif.setTitle(new String(notification.getTitle().getBytes(), StandardCharsets.UTF_8));
        notif.setContent(new String(notification.getContent().getBytes(), StandardCharsets.UTF_8));

        notificationRepository.save(notificationToEntityTranslator.toEntity(notif)
                                                                  .withUser(userToEntityTranslator.toEntity(user)));
    }

    public void createAllNotificationForUsers(Notification notification, List<User> allUser)
    {

        notificationRepository.saveAll(allUser.stream()
                                              .map(userToEntityTranslator::toEntity)
                                              .map(userEntity -> notificationToEntityTranslator
                                                      .toEntity(notification)
                                                      .withUser(userEntity))
                                              .collect(Collectors.toList()));

    }

    public Set<Notification> getAllNotificationsForUserWithService(String userId, Service service)
    {
        return notificationRepository.findAllForUserWithService(userId, serviceToEntityTranslator.toEntity(service))
                                     .stream()
                                     .map(notificationToEntityTranslator::toModel).collect(Collectors.toSet());
    }

    public Set<Notification> getAllNotificationsForUserWithDateSince(String userId, Date date)
    {
        return notificationRepository.findAllForUserWithDateSince(userId, date)
                                     .stream()
                                     .map(notificationToEntityTranslator::toModel).collect(Collectors.toSet());
    }

    public Set<Notification> getAllNotificationsForUserWithServiceAndDateSince(String userID, Service service, Date date)
    {
        return notificationRepository.findAllForUserWithServiceAndDateSince(userID,
                                                                            serviceToEntityTranslator.toEntity(service),
                                                                            date)
                                     .stream()
                                     .map(notificationToEntityTranslator::toModel).collect(Collectors.toSet());
    }

    public NotificationRepository getNotificationRepository()
    {
        return notificationRepository;
    }

    public void setNotificationRepository(NotificationRepository notificationRepository)
    {
        this.notificationRepository = notificationRepository;
    }

    public NotificationToEntityTranslator getNotificationToEntityTranslator()
    {
        return notificationToEntityTranslator;
    }

    public void setNotificationToEntityTranslator(NotificationToEntityTranslator notificationToEntityTranslator)
    {
        this.notificationToEntityTranslator = notificationToEntityTranslator;
    }

    public UserToEntityTranslator getUserToEntityTranslator()
    {
        return userToEntityTranslator;
    }

    public void setUserToEntityTranslator(UserToEntityTranslator userToEntityTranslator)
    {
        this.userToEntityTranslator = userToEntityTranslator;
    }

    public ServiceToEntityTranslator getServiceToEntityTranslator()
    {
        return serviceToEntityTranslator;
    }

    public void setServiceToEntityTranslator(ServiceToEntityTranslator serviceToEntityTranslator)
    {
        this.serviceToEntityTranslator = serviceToEntityTranslator;
    }
}
