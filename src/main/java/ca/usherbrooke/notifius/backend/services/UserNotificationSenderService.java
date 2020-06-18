package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderKey;
import ca.usherbrooke.notifius.backend.repositories.UserNotificationSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserNotificationSenderService
{
    @Autowired
    private UserNotificationSenderRepository userNotificationSenderRepository;

    public Optional<String> getValueIfExists(String userId, String notificationSenderId)
    {
        return userNotificationSenderRepository
                .findById(new UserNotificationSenderKey(userId,notificationSenderId))
                .map(UserNotificationSenderEntity::getValue);
    }
}
