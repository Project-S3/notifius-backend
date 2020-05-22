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
        UserEntity user = new UserEntity(userId);
        userRepository.save(user);
        SettingsEntity settings = new SettingsEntity(user);
        settingsRepository.save(settings);
        return user;
    }
}
