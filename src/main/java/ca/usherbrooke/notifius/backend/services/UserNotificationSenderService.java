package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.NotificationSenderEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderKey;
import ca.usherbrooke.notifius.backend.repositories.NotificationSenderRepository;
import ca.usherbrooke.notifius.backend.repositories.UserNotificationSenderRepository;
import ca.usherbrooke.notifius.backend.resterrors.NotificationSenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationSenderService
{
    @Autowired
    private NotificationSenderRepository notificationSenderRepository;
    @Autowired
    private UserNotificationSenderRepository userNotificationSenderRepository;

    public String getAttributeIfExists(String userId, String notificationSenderId)
    {
        UserNotificationSenderEntity userNotificationSenderEntity = userNotificationSenderRepository
                .findById(new UserNotificationSenderKey(userId,notificationSenderId))
                .orElse(null);
        if(userNotificationSenderEntity != null)
        {
            return userNotificationSenderEntity.getAttribute();
        }
        else
        {
            return null;
        }
    }
}
