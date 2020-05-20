package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.models.Settings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SettingsController
{
    @GetMapping("/users/{userID}/settings")
    @ResponseStatus(code = HttpStatus.OK)
    public Settings getSettingByUser(@PathVariable("userID") String userID)
    {
        return null;
    }

    @PostMapping("/users/{userID}/settings")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Settings setSettingByUser(@PathVariable("userID") String userID)
    {
        return null;
    }
}
