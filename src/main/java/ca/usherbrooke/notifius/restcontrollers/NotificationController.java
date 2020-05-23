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
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

// todo faudrait faire des meilleur valid des param et plus d'erreur pour donnée un feedback faire de quoi de plus propre pour sanitize les donnés

@RestController
public class NotificationController
{
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

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
    public Set<Notification> getNotifications(@PathVariable("userId") String userIdParam,
                                              @RequestParam(value = "service", required = false) String serviceParam,
                                              @RequestParam(value = "date", required = false) String dateParam)
    {
        String userId = userIdParam.toLowerCase();
        User user = userService.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Service service = null;
        if (StringUtils.hasText(serviceParam)) {
            try {
                service = Service.valueOf(serviceParam.strip().toUpperCase());
            } catch (IllegalArgumentException e) {
                // todo on fait quoi si le service existe pas
            }
        }
        Date date = null;
        if (StringUtils.hasText(dateParam)) {
            try {
                date = dateFormat.parse(dateParam.strip());
            } catch (ParseException e) {
                // todo on fait quoi si la date est mal formaté
            }
        }

        if (service != null && date != null) {
            return notificationService.getAllNotificationsForUserWithServiceAndDateSince(userId, service, date);
        }
        if (service != null) {
            return notificationService.getAllNotificationsForUserWithService(userId, service);
        }
        if (date != null) {
            return notificationService.getAllNotificationsForUserWithDateSince(userId, date);
        }
        return user.getNotifications();
    }

    @PostMapping(path = "/users/{userId}/notifications",
                 consumes = "application/json")
    public ResponseEntity<Notification> createNotificationByUser(@PathVariable("userId") String userIdParam,
                                                                @RequestBody Notification notification)
    {
        String userId = userIdParam.toLowerCase();
        notificationValidator.validNotificationThrowIfNotValid(notification);

        if (notificationService.createOrUpdateNotification(notification, userId)) {
            notificationSenderService.sendNotifications(notification, userId);
        } else {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
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
