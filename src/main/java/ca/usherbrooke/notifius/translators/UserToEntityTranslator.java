package ca.usherbrooke.notifius.translators;

import ca.usherbrooke.notifius.entities.UserEntity;
import ca.usherbrooke.notifius.models.User;
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
                .withSettings(settingsToEntityTranslator.toModel(userEntity.getSettings()))
                .withNotifications(userEntity.getNotifications()
                                             .stream()
                                             .map(notificationToEntityTranslator::toModel)
                                             .collect(Collectors.toSet()));
    }
}
