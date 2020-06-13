package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.SettingsEntity;
import ca.usherbrooke.notifius.backend.entities.UserEntity;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.repositories.ServiceRepository;
import ca.usherbrooke.notifius.backend.repositories.SettingsRepository;
import ca.usherbrooke.notifius.backend.repositories.UserRepository;
import ca.usherbrooke.notifius.backend.translators.UserToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private UserToEntityTranslator userToEntityTranslator;

    @Value("${notifius.email.domain}")
    private String notifiusEmailDomain;

    public void createUser(String userId)
    {
        SettingsEntity settingsEntity = constructSettingEntity();
        settingsRepository.save(settingsEntity);
        userRepository.save(constructUserEntity(userId, settingsEntity));
    }

    public List<User> createAllUser(List<String> allUserId)
    {
        List<SettingsEntity> settingsEntities = new ArrayList<>();
        List<UserEntity> userEntities = new ArrayList<>();

        for (String id : allUserId) {
            SettingsEntity settingsEntity = constructSettingEntity();
            settingsEntities.add(settingsEntity);
            userEntities.add(constructUserEntity(id, settingsEntity));
        }

        settingsRepository.saveAll(settingsEntities);
        userRepository.saveAll(userEntities);

        return userEntities.stream()
                           .map(userToEntityTranslator::toModel)
                           .collect(Collectors.toList());
    }

    public void updateUser(User user)
    {
        userRepository.save(userToEntityTranslator.toEntity(user));
    }

    public Optional<User> getUser(String userId)
    {
        return userRepository.findById(userId).map(userToEntityTranslator::toModel);
    }

    public List<User> getAllUser(List<String> allUserId)
    {
        return userRepository.findAllById(allUserId)
                             .stream()
                             .map(userToEntityTranslator::toModel)
                             .collect(Collectors.toList());
    }

    private SettingsEntity constructSettingEntity()
    {
        return new SettingsEntity().withEmailServiceEnable(true)
                                   .withSmsServiceEnable(false)
                                   .withHttpServiceEnable(false)
                                   .withDiscordWebhookEnable(false)
                                   .withEnableServices(new HashSet<>(serviceRepository.findAll()));
    }

    private UserEntity constructUserEntity(String userId, SettingsEntity settingsEntity)
    {
        return new UserEntity().withId(userId)
                               .withEmail(String.format("%s@%s", userId, notifiusEmailDomain))
                               .withSettings(settingsEntity)
                               .withNotifications(new HashSet<>());
    }
}
