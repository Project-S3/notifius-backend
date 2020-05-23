package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.Service;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.resterrors.UserNotFoundException;
import ca.usherbrooke.notifius.services.NotificationSenderService;
import ca.usherbrooke.notifius.services.NotificationService;
import ca.usherbrooke.notifius.services.UserService;
import ca.usherbrooke.notifius.validators.NotificationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@RestController
public class NotificationController
{
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationValidator notificationValidator;
    @Autowired
    private NotificationSenderService notificationSenderService;
    @Autowired
    private NotificationService notificationService;


    // todo need to be restricted
    @GetMapping(path = "/users/{userId}/notifications",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Set<Notification> getNotifications(@PathVariable("userId") String userId,
                                              @RequestParam(value = "service", required = false) String service,
                                              @RequestParam(value = "date", required = false) String date)
            throws ParseException
    {
        // todo better error handling
        User user = userService.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (StringUtils.hasText(service) && StringUtils.hasText(date)) {
            Service s = Service.valueOf(service.strip());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date d =  dateFormat.parse(date.strip());
            return notificationService.getAllNotificationsForUserWithServiceAndDateSince(userId, s, d);
        }
        if (StringUtils.hasText(service)) {
            Service s = Service.valueOf(service.strip());
            return notificationService.getAllNotificationsForUserWithService(userId, s);
        }
        if (StringUtils.hasText(date)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date d =  dateFormat.parse(date.strip());
            return notificationService.getAllNotificationsForUserWithDateSince(userId, d);
        }

        return user.getNotifications();
    }

    @PostMapping(path = "/users/{userId}/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationByUser(@PathVariable("userId") String userId,
                                                 @RequestBody Notification notification)
    {
        //todo quand j'envoie une notif deux fois de suite elle ne devrait pas etre deux fois en bd
        notificationSenderService.sendNotifications(notification,userId);
        return notification;
    }

    @PostMapping(path = "/trimester/{trimesterId}/activities/{activityId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByActivity(@PathVariable("trimesterId") String trimesterId,
                                                      @PathVariable("activityId") String activityId,
                                                      @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        return notification;
    }

    @PostMapping(path = "/departments/{departmentId}/sessions/{sessionId}/trimesters/{trimesterId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByDepartment(@PathVariable("departmentId") String departmentId,
                                                        @PathVariable("sessionId") String sessionId,
                                                        @PathVariable("trimesterId") String trimesterId,
                                                        @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        return notification;
    }
}
