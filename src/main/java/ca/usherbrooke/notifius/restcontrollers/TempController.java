package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


// todo enlever a la fin, utilisé pour tester
@RestController
@RequestMapping("temp")
public class TempController
{
    @Autowired
    private UserService userService;

    @GetMapping(path = "/create",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public String getNotifications()
    {
        userService.createUser("gram3504");
        return "ok";
    }
}
