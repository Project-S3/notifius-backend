package ca.usherbrooke.notifius.services;

import ca.usherbrooke.notifius.entities.ServiceEntity;
import ca.usherbrooke.notifius.entities.SettingsEntity;
import ca.usherbrooke.notifius.entities.UserEntity;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.repositories.ServiceRepository;
import ca.usherbrooke.notifius.repositories.SettingsRepository;
import ca.usherbrooke.notifius.repositories.UserRepository;
import ca.usherbrooke.notifius.translators.UserToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        Set<ServiceEntity> allService = new HashSet<>(serviceRepository.findAll());
        SettingsEntity settingsEntity = new SettingsEntity().withEmailServiceEnable(true)
                                                            .withSmsServiceEnable(false)
                                                            .withEnableServices(allService);
        settingsRepository.save(settingsEntity);

        userRepository.save(new UserEntity().withId(userId)
                                            .withEmail(String.format("%s@%s", userId, notifiusEmailDomain))
                                            .withSettings(settingsEntity)
                                            .withNotifications(new HashSet<>()));
    }

    public void updateUser(User user)
    {
        userRepository.save(userToEntityTranslator.toEntity(user));
    }

    public Optional<User> getUser(String userId)
    {
        return userRepository.findById(userId).map(userToEntityTranslator::toModel);
    }
}
