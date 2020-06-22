package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.UserEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderEntity;
import ca.usherbrooke.notifius.backend.models.Settings;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.repositories.ServiceRepository;
import ca.usherbrooke.notifius.backend.repositories.UserNotificationSenderRepository;
import ca.usherbrooke.notifius.backend.repositories.UserRepository;
import ca.usherbrooke.notifius.backend.translators.UserToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SettingsService
{
    @Autowired
    private UserNotificationSenderRepository userNotificationSenderRepository;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserToEntityTranslator userToEntityTranslator;

    public Settings getSettings(User user)
    {
        Map<String, String> notificationSenders = new HashMap<>();
        for (UserNotificationSenderEntity uns : userNotificationSenderRepository
                .getUserNotificationSender(user.getId())) {
            notificationSenders.put(uns.getNotificationSender().getId(), uns.getValue());
        }
        return new Settings().withEnableServices(user.getEnableServices())
                             .withNotificationSenders(notificationSenders);
    }

    public void updateSettings(User user, Settings newSettings)
    {
        Settings oldSettings = getSettings(user);

        if (!user.getEnableServices().equals(newSettings.getEnableServices())) {
            userService.updateUser(user.withEnableServices(newSettings.getEnableServices()));
        }

        UserEntity userEntity = userToEntityTranslator.toEntity(user);
        for (Map.Entry<String, String> notificationSender : newSettings.getNotificationSenders().entrySet()) {
            userNotificationSenderService.createOrUpdate(userEntity,
                                                         notificationSender.getKey(),
                                                         notificationSender.getValue());
        }
        for (Map.Entry<String, String> notificationSender : oldSettings.getNotificationSenders().entrySet()) {
            if (!newSettings.getNotificationSenders().containsKey(notificationSender.getKey())) {
                userNotificationSenderService.delete(userEntity, notificationSender.getKey());
            }
        }
    }
}
