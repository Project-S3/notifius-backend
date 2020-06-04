package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.Service;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.resterrors.UserNotFoundException;
import ca.usherbrooke.notifius.services.NotificationSenderService;
import ca.usherbrooke.notifius.services.NotificationService;
import ca.usherbrooke.notifius.services.UserService;
import ca.usherbrooke.notifius.validators.NotificationValidator;
import ca.usherbrooke.notifius.zeuz.clients.ZeuzUsersByGroupClient;
import ca.usherbrooke.notifius.zeuz.models.UserByGroup;
import org.bouncycastle.util.Integers;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private ZeuzUsersByGroupClient zeuzUsersByGroupClient;

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
            return new ResponseEntity<>(notification, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/trimester/{trimesterId}/activities/{activityId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByActivity(@PathVariable("trimesterId") String trimesterId,
                                                      @PathVariable("activityId") String activityId,
                                                      @RequestBody Notification notification)
    {
        String tId = trimesterId.toUpperCase().strip();
        String aId = activityId.toLowerCase().strip();

        Calendar calendar = new GregorianCalendar();
        calendar.set(2000 + Integers.valueOf(Integer.parseInt(tId.substring(1, 2))), Calendar.APRIL, 1);

        zeuzUsersByGroupClient.getUsers(calendar.getTime(), tId)
                                .stream()
                                .filter(userByGroup -> aId.equals(userByGroup.getActivityId()))
                                .map(UserByGroup::getUserId)
                                .distinct()
                                .forEach(userId -> notificationSenderService.sendNotifications(notification, userId));
        return notification;
    }

    @PostMapping(path = "/trimesters/{trimesterId}/profile/{profileId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByDepartment(@PathVariable("trimesterId") String trimesterId,
                                                        @PathVariable("profileId") String profileId,
                                                        @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        trimesterId = trimesterId.strip().toUpperCase();
        profileId = profileId.strip().toLowerCase();

        Calendar calendar = new GregorianCalendar();
        calendar.set(2000 + Integers.valueOf(Integer.parseInt(profileId.substring(1, 2))), Calendar.APRIL, 1);
        zeuzUsersByGroupClient.getUsers(calendar.getTime(), profileId)
                              .stream()
                              .map(UserByGroup::getUserId)
                              .distinct()
                              .forEach(userID -> {
                                  notificationSenderService.sendNotifications(notification, userID);
                              });

        return notification;
    }
}
