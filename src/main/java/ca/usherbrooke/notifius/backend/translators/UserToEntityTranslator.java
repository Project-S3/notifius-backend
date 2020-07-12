/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.translators;

import ca.usherbrooke.notifius.backend.entities.UserEntity;
import ca.usherbrooke.notifius.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserToEntityTranslator
{
    @Autowired
    private ServiceToEntityTranslator serviceToEntityTranslator;
    @Autowired
    private NotificationToEntityTranslator notificationToEntityTranslator;

    public UserEntity toEntity(User user)
    {
        return new UserEntity().withId(user.getId())
                               .withNotifications(user.getNotifications()
                                                      .stream()
                                                      .map(notificationToEntityTranslator::toEntity)
                                                      .collect(Collectors.toSet()))
                               .withEnableServices(user.getEnableServices()
                                                       .stream()
                                                       .map(serviceToEntityTranslator::toEntity)
                                                       .collect(Collectors.toSet()));
    }

    public User toModel(UserEntity userEntity)
    {
        return new User().withId(userEntity.getId())
                         .withNotifications(userEntity.getNotifications()
                                                      .stream()
                                                      .map(notificationToEntityTranslator::toModel)
                                                      .collect(Collectors.toSet()))
                         .withEnableServices(userEntity.getEnableServices()
                                                       .stream()
                                                       .map(serviceToEntityTranslator::toModel)
                                                       .collect(Collectors.toSet()));
    }

    public ServiceToEntityTranslator getServiceToEntityTranslator()
    {
        return serviceToEntityTranslator;
    }

    public void setServiceToEntityTranslator(ServiceToEntityTranslator serviceToEntityTranslator)
    {
        this.serviceToEntityTranslator = serviceToEntityTranslator;
    }

    public NotificationToEntityTranslator getNotificationToEntityTranslator()
    {
        return notificationToEntityTranslator;
    }

    public void setNotificationToEntityTranslator(NotificationToEntityTranslator notificationToEntityTranslator)
    {
        this.notificationToEntityTranslator = notificationToEntityTranslator;
    }
}
