package ca.usherbrooke.notifius.backend.restcontrollers;

import ca.usherbrooke.notifius.backend.models.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SettingsController
{
    private final Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @GetMapping(path = "/restricted/users/{userID}/settings",
                produces = "application/json")
    public ResponseEntity<Settings> getSettingByUser(@PathVariable("userID") String userID)
    {
        Settings body = new Settings();
        HttpHeaders headers = new HttpHeaders();
        headers.add("is-auth", "true");
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @PostMapping(path = "/restricted/users/{userID}/settings",
                 consumes = "application/json")
    public ResponseEntity<Settings> setSettingByUser(@PathVariable("userID") String userID,
                                                     @RequestBody Settings settings)
    {
        Settings body = new Settings();
        HttpHeaders headers = new HttpHeaders();
        headers.add("is-auth", "true");
        return new ResponseEntity<>(body, headers, HttpStatus.CREATED);
    }
}
