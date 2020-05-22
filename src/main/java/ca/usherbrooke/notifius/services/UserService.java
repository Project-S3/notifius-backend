package ca.usherbrooke.notifius.services;

import ca.usherbrooke.notifius.entities.SettingsEntity;
import ca.usherbrooke.notifius.entities.UserEntity;
import ca.usherbrooke.notifius.repositories.SettingsRepository;
import ca.usherbrooke.notifius.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SettingsRepository settingsRepository;

    public UserEntity createUser(String userId)
    {
        SettingsEntity settings = new SettingsEntity();
        settingsRepository.save(settings);
        UserEntity user = new UserEntity(userId, settings);
        userRepository.save(user);
        return user;
    }
}
