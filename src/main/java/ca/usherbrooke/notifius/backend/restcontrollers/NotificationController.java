/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.restcontrollers;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.resterrors.UserNotFoundException;
import ca.usherbrooke.notifius.backend.services.NotificationSenderService;
import ca.usherbrooke.notifius.backend.services.NotificationService;
import ca.usherbrooke.notifius.backend.services.UserService;
import ca.usherbrooke.notifius.backend.validators.NotificationValidator;
import ca.usherbrooke.notifius.backend.zeuz.clients.ZeuzUsersByGroupClient;
import ca.usherbrooke.notifius.backend.zeuz.models.UserByGroup;
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
    public Set<Notification> getNotifications(@PathVariable("userId") String userId,
                                              @RequestParam(value = "service", required = false) String serviceParam,
                                              @RequestParam(value = "date", required = false) String dateParam)
    {
        String finalUserId = sanitizeUserId(userId);
        serviceParam = sanitizeService(serviceParam);
        dateParam = sanitizeDate(dateParam);

        User user = userService.getUser(finalUserId).orElseThrow(() -> new UserNotFoundException(finalUserId));

        Service service = null;
        if (StringUtils.hasText(serviceParam)) {
            try {
                service = Service.valueOf(serviceParam);
            } catch (IllegalArgumentException e) {
                // todo on fait quoi si le service existe pas
            }
        }
        Date date = null;
        if (StringUtils.hasText(dateParam)) {
            try {
                date = dateFormat.parse(dateParam);
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
    public ResponseEntity<Notification> createNotificationByUser(@PathVariable("userId") String userId,
                                                                 @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);
        userId = sanitizeUserId(userId);

        User user = userService.getUser(userId).orElseThrow(UserNotFoundException::new);

        if (notificationService.create(notification, user)) {
            notificationSenderService.sendNotifications(notification, user);
            return new ResponseEntity<>(notification, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/trimester/{trimesterId}/activities/{activityId}/users/notifications",
                 consumes = "application/json")
    public ResponseEntity<Notification> createNotificationsByActivity(@PathVariable("trimesterId") String trimesterId,
                                                                      @PathVariable("activityId") String activityId,
                                                                      @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        trimesterId = sanitizeTrimesterId(trimesterId);
        String finalActivityId = sanitizeActivityId(activityId);

        Calendar calendar = new GregorianCalendar();
        calendar.set(2000 + Integer.parseInt(trimesterId.substring(1, 3)), Calendar.APRIL, 1);


        List<String> allUserId = zeuzUsersByGroupClient.getUsers(calendar.getTime(), trimesterId)
                                                       .stream()
                                                       .filter(userByGroup -> finalActivityId
                                                               .equals(userByGroup.getActivityId()))
                                                       .map(UserByGroup::getUserId)
                                                       .distinct()
                                                       .collect(Collectors.toList());

        if (saveNotificationAndImportUserIfNotExist(notification, allUserId)) {
            return new ResponseEntity<>(notification, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/trimesters/{trimesterId}/profiles/{profileId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Notification> createNotificationsByDepartment(@PathVariable("trimesterId") String trimesterId,
                                                                        @PathVariable("profileId") String profileId,
                                                                        @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        trimesterId = sanitizeTrimesterId(trimesterId);
        profileId = sanitizeProfileId(profileId);

        String finalProfilId = profileId;

        Calendar calendar = new GregorianCalendar();
        calendar.set(2000 + Integer.parseInt(trimesterId.substring(1, 3)), Calendar.APRIL, 1);
        List<String> allUserId = zeuzUsersByGroupClient.getUsers(calendar.getTime(), trimesterId, profileId)
                                                       .stream()
                                                       .filter(userByGroup -> finalProfilId
                                                               .equals(userByGroup.getProfileId()))
                                                       .map(UserByGroup::getUserId)
                                                       .distinct()
                                                       .collect(Collectors.toList());

        if (saveNotificationAndImportUserIfNotExist(notification, allUserId)) {
            return new ResponseEntity<>(notification, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }
    }

    private boolean saveNotificationAndImportUserIfNotExist(Notification notification, List<String> allUserId)
    {
        List<User> allUser = userService.getAllUser(allUserId);

        allUserId.removeAll(allUser.stream().map(User::getId).collect(Collectors.toList()));
        allUser.addAll(userService.createAllUser(allUserId));

        boolean created = notificationService.createAllNotificationForUsers(notification, allUser);

        if (created) {
            sendNotificationForAllUsers(notification, allUser);
        }

        return created;
    }

    public void sendNotificationForAllUsers(Notification notification, List<User> allUser)
    {
        allUser.parallelStream().forEach(user -> notificationSenderService.sendNotifications(notification, user));
    }

    private String sanitizeUserId(String userId)
    {
        if (userId == null) return null;
        return userId.strip().toLowerCase();
    }

    private String sanitizeService(String service)
    {
        if (service == null) return null;
        return service.strip().toUpperCase();
    }

    private String sanitizeDate(String date)
    {
        if (date == null) return null;
        return date.strip();
    }

    private String sanitizeTrimesterId(String trimesterId)
    {
        if (trimesterId == null) return null;
        return trimesterId.strip().toUpperCase();
    }

    private String sanitizeActivityId(String activityId)
    {
        if (activityId == null) return null;
        return activityId.strip().toLowerCase();
    }

    private String sanitizeProfileId(String profileId)
    {
        if (profileId == null) return null;
        return profileId.strip().toLowerCase();
    }

}
