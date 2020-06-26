/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.repositories.NotificationRepository;
import ca.usherbrooke.notifius.backend.repositories.UserRepository;
import ca.usherbrooke.notifius.backend.translators.NotificationToEntityTranslator;
import ca.usherbrooke.notifius.backend.translators.ServiceToEntityTranslator;
import ca.usherbrooke.notifius.backend.translators.UserToEntityTranslator;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class NotificationService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationToEntityTranslator notificationToEntityTranslator;
    @Autowired
    private UserToEntityTranslator userToEntityTranslator;
    @Autowired
    private ServiceToEntityTranslator serviceToEntityTranslator;

    public boolean create(Notification notification, User user)
    {
        try {
            notificationRepository.save(notificationToEntityTranslator.toEntity(notification)
                                                                      .withUser(userToEntityTranslator.toEntity(user)));
            return true;
        } catch (Exception e) {
            if (((ConstraintViolationException) e.getCause()).getSQLException().getSQLState().equals("23505")) {
                return false;
            } else throw e;
        }
    }

    public boolean createAllNotificationForUsers(Notification notification, List<User> allUser)
    {
        try {
            notificationRepository.saveAll(allUser.stream()
                                                  .map(userToEntityTranslator::toEntity)
                                                  .map(userEntity -> notificationToEntityTranslator
                                                          .toEntity(notification)
                                                          .withUser(userEntity))
                                                  .collect(Collectors.toList()));
            return true;
        } catch (Exception e) {
            if (((ConstraintViolationException) e.getCause()).getSQLException().getSQLState().equals("23505")) {
                return false;
            } else throw e;
        }
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
}
