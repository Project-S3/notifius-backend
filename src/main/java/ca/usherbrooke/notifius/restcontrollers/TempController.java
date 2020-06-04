package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.services.UserService;
import ca.usherbrooke.notifius.zeuz.clients.ZeuzNewsClient;
import ca.usherbrooke.notifius.zeuz.clients.ZeuzTrimesterClient;
import ca.usherbrooke.notifius.zeuz.clients.ZeuzUsersByGroupClient;
import ca.usherbrooke.notifius.zeuz.models.News;
import ca.usherbrooke.notifius.zeuz.models.Trimester;
import ca.usherbrooke.notifius.zeuz.models.UserByGroup;
import org.bouncycastle.util.Integers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


// todo enlever a la fin, utilisé pour tester
@RestController
@RequestMapping("temp")
public class TempController
{
    @Autowired
    private UserService userService;
    @Autowired
    private ZeuzTrimesterClient zeuzTrimesterClient;
    @Autowired
    private ZeuzNewsClient zeuzNewsClient;
    @Autowired
    private ZeuzUsersByGroupClient zeuzUsersByGroupClient;

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
        c.set(2019, Calendar.APRIL, 1);

        return zeuzTrimesterClient.getTrimester(c.getTime(), null);
    }

    @GetMapping(path = "/zeuz/news",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<News> getNews()
    {
        Calendar c = new GregorianCalendar();
        c.set(2019, Calendar.APRIL, 1);

        return zeuzNewsClient.getNews(c.getTime(), null, "21073");
    }

    @GetMapping(path = "/zeuz/user-group",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Set<UserByGroup> getUser()
    {
        String tId = "E20";
        String aId = "s3iapp1";

        Calendar c = new GregorianCalendar();
        c.set(2000 + Integers.valueOf(Integer.parseInt(tId.substring(1, 2))), Calendar.APRIL, 1);

        return zeuzUsersByGroupClient.getUsers(c.getTime(), tId)
                                     .stream()
                                     .filter(userByGroup -> aId.equals(userByGroup.getActivityId()))
                                     .collect(Collectors.toCollection(HashSet::new));
    }
}

