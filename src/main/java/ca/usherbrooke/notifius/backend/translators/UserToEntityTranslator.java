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
    private SettingsToEntityTranslator settingsToEntityTranslator;
    @Autowired
    private NotificationToEntityTranslator notificationToEntityTranslator;

    public UserEntity toEntity(User user)
    {
        return new UserEntity().withId(user.getId())
                               .withEmail(user.getEmail())
                               .withPhoneNumber(user.getPhoneNumber())
                               .withCustomUrl(user.getCustomUrl())
                               .withDiscordWebhookUrl(user.getDiscordWebhookUrl())
                               .withTeamsWebhookUrl(user.getTeamsWebhookUrl())
                               .withSlackWebhookUrl(user.getSlackWebhookUrl())
                               .withSettings(settingsToEntityTranslator.toEntity(user.getSettings()))
                               .withNotifications(user.getNotifications()
                                                      .stream()
                                                      .map(notificationToEntityTranslator::toEntity)
                                                      .collect(Collectors.toSet()));
    }

    public User toModel(UserEntity userEntity)
    {
        return new User().withId(userEntity.getId())
                         .withEmail(userEntity.getEmail())
                         .withPhoneNumber(userEntity.getPhoneNumber())
                         .withCustomUrl(userEntity.getCustomUrl())
                         .withDiscordWebhookUrl(userEntity.getDiscordWebhookUrl())
                         .withTeamsWebhookUrl(userEntity.getTeamsWebhookUrl())
                         .withSlackWebhookUrl(userEntity.getSlackWebhookUrl())
                         .withSettings(settingsToEntityTranslator.toModel(userEntity.getSettings()))
                         .withNotifications(userEntity.getNotifications()
                                                      .stream()
                                                      .map(notificationToEntityTranslator::toModel)
                                                      .collect(Collectors.toSet()));
    }
}
