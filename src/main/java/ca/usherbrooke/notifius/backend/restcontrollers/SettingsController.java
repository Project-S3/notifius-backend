package ca.usherbrooke.notifius.backend.restcontrollers;

import ca.usherbrooke.notifius.backend.models.Settings;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.resterrors.UserNotFoundException;
import ca.usherbrooke.notifius.backend.services.SettingsService;
import ca.usherbrooke.notifius.backend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SettingsController
{
    private final Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private SettingsService settingsService;

    @GetMapping(path = "/users/{userID}/settings",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Settings getSettingByUser(@PathVariable("userID") String userID)
    {
        return userService.getUser(userID)
                          .map(settingsService::getSettings)
                          .orElseThrow(() -> new UserNotFoundException(userID));
    }

    @PostMapping(path = "/users/{userID}/settings",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Settings setSettingByUser(@PathVariable("userID") String userID,
                                                     @RequestBody Settings settings)
    {
        User user = userService.getUser(userID).orElseThrow(() -> new UserNotFoundException(userID));
        settingsService.updateSettings(user, settings);
        return settings;
    }
}
