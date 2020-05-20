package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.models.Settings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SettingsController
{
    @GetMapping(value = "/users/{userID}/settings",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Settings getSettingByUser(@PathVariable("userID") String userID)
    {
        return null;
    }

    @PostMapping(value = "/users/{userID}/settings",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Settings setSettingByUser(@PathVariable("userID") String userID,
                                     @RequestBody Settings settings)
    {
        return null;
    }
}
