package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.UserEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderKey;
import ca.usherbrooke.notifius.backend.repositories.NotificationSenderRepository;
import ca.usherbrooke.notifius.backend.repositories.UserNotificationSenderRepository;
import ca.usherbrooke.notifius.backend.resterrors.NotificationSenderNotFoundException;
import ca.usherbrooke.notifius.backend.translators.UserToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserNotificationSenderService
{
    @Autowired
    private UserNotificationSenderRepository userNotificationSenderRepository;
    @Autowired
    private NotificationSenderRepository notificationSenderRepository;

    public Optional<String> getValueIfExists(String userId, String notificationSenderId)
    {
        return userNotificationSenderRepository
                .findById(new UserNotificationSenderKey(userId,notificationSenderId))
                .map(UserNotificationSenderEntity::getValue);
    }

    public void createOrUpdate(UserEntity userEntity, String notificationSenderId, String value)
    {
         userNotificationSenderRepository.save(new UserNotificationSenderEntity()
                                                      .withNotificationSender(notificationSenderRepository
                                                                                      .findById(notificationSenderId)
                                                                                      .orElseThrow(
                                                                                              NotificationSenderNotFoundException::new))
                                                      .withUser(userEntity)
                                                      .withValue(value)
                                                      .withId(new UserNotificationSenderKey(userEntity.getId(),
                                                                                            notificationSenderId)));
    }

    public void delete(UserEntity userEntity, String notificationSenderId)
    {
        userNotificationSenderRepository.delete(new UserNotificationSenderEntity()
                                                        .withNotificationSender(notificationSenderRepository
                                                                                        .findById(notificationSenderId)
                                                                                        .orElseThrow(
                                                                                                NotificationSenderNotFoundException::new))
                                                        .withUser(userEntity)
                                                        .withId(new UserNotificationSenderKey(userEntity.getId(),
                                                                                              notificationSenderId)));
    }


}
