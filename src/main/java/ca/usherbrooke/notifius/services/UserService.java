package ca.usherbrooke.notifius.services;

import ca.usherbrooke.notifius.entities.SettingsEntity;
import ca.usherbrooke.notifius.entities.UserEntity;
import ca.usherbrooke.notifius.repositories.ServiceRepository;
import ca.usherbrooke.notifius.repositories.SettingsRepository;
import ca.usherbrooke.notifius.repositories.UserRepository;
import com.twilio.rest.chat.v1.ServiceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    public UserEntity createUser(String userId)
    {
        SettingsEntity settings = new SettingsEntity();
        settings.setEnableServices(new HashSet<>(serviceRepository.findAll()));
        settingsRepository.save(settings);
        UserEntity user = new UserEntity(userId, settings);
        userRepository.save(user);
        return user;


    }

    public Optional<UserEntity> getUser(String userId)
    {
        return userRepository.findById(userId);
    }

}
