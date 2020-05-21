package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.models.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public class SettingsController
{
    private final Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @GetMapping(value = "/auth/users/{userID}/settings",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Settings getSettingByUser(@PathVariable("userID") String userID)
    {
        return new Settings();
    }

    @PostMapping(value = "/auth/users/{userID}/settings",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Settings setSettingByUser(@PathVariable("userID") String userID,
                                     @RequestBody Settings settings)
    {
        return new Settings();
    }
}
