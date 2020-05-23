package ca.usherbrooke.notifius.services;

import ca.usherbrooke.notifius.entities.UserEntity;
import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.Service;
import ca.usherbrooke.notifius.repositories.NotificationRepository;
import ca.usherbrooke.notifius.repositories.UserRepository;
import ca.usherbrooke.notifius.resterrors.UserNotFoundException;
import ca.usherbrooke.notifius.translators.NotificationToEntityTranslator;
import ca.usherbrooke.notifius.translators.ServiceToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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
    private ServiceToEntityTranslator serviceToEntityTranslator;

    public void saveNotification(Notification notification, String userId)
    {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        notificationRepository.save(notificationToEntityTranslator.toEntity(notification)
                                                                  .withUser(userEntity));
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
