package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.services.UserService;
import ca.usherbrooke.notifius.zeuz.clients.ZeuzTrimestersClient;
import ca.usherbrooke.notifius.zeuz.models.Trimester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


// todo enlever a la fin, utilisé pour tester
@RestController
@RequestMapping("temp")
public class TempController
{
    @Autowired
    private UserService userService;
    @Autowired
    private ZeuzTrimestersClient zeuzTrimesterClient;

    @GetMapping(path = "/create",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public String getNotifications()
    {
        userService.createUser("gram3504");
        return "ok";
    }

    @GetMapping(path = "/zeuz/trimesters",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Trimester> getTrimester()
    {
        Calendar c = new GregorianCalendar();
        c.set(2019, Calendar.APRIL , 1);

        return zeuzTrimesterClient.getTrimester(c.getTime(), null);
    }
}
