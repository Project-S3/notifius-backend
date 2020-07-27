/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.restcontrollers;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.resterrors.DateInvalidException;
import ca.usherbrooke.notifius.backend.resterrors.UserNotFoundException;
import ca.usherbrooke.notifius.backend.services.NotificationSenderService;
import ca.usherbrooke.notifius.backend.services.NotificationService;
import ca.usherbrooke.notifius.backend.services.ServiceService;
import ca.usherbrooke.notifius.backend.services.UserService;
import ca.usherbrooke.notifius.backend.validators.NotificationValidator;
import ca.usherbrooke.notifius.backend.zeuz.clients.ZeuzUsersByGroupClient;
import ca.usherbrooke.notifius.backend.zeuz.models.UserByGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.JANUARY;

@RestController
public class NotificationController
{
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    private ServiceService serviceService;
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
            service = serviceService.getServiceByName(serviceParam);
        }

        Date date = null;
        if (StringUtils.hasText(dateParam)) {
            try {
                date = dateFormat.parse(dateParam);
            } catch (ParseException e) {
                throw new DateInvalidException();
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
    @ResponseStatus(HttpStatus.CREATED)
    public Notification createNotificationByUser(@PathVariable("userId") String userId,
                                                 @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);
        userId = sanitizeUserId(userId);

        User user = userService.getUser(userId).orElseThrow(UserNotFoundException::new);

        notificationService.create(notification, user);
        notificationSenderService.sendNotifications(notification, user);

        return notification;
    }

    @PostMapping(path = "/trimester/{trimesterId}/activities/{activityId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Notification createNotificationsByActivity(@PathVariable("trimesterId") String trimesterId,
                                                      @PathVariable("activityId") String activityId,
                                                      @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        trimesterId = sanitizeTrimesterId(trimesterId);
        String finalActivityId = sanitizeActivityId(activityId);

        Calendar calendar = new GregorianCalendar();
        calendar.set(2000 + Integer.parseInt(trimesterId.substring(1, 3)), JANUARY, 1);

        List<String> allUserId = zeuzUsersByGroupClient.getUsers(calendar.getTime(), trimesterId)
                                                       .stream()
                                                       .filter(userByGroup -> finalActivityId
                                                               .equals(userByGroup.getActivityId()))
                                                       .map(UserByGroup::getUserId)
                                                       .distinct()
                                                       .collect(Collectors.toList());

        saveNotificationAndImportUserIfNotExist(notification, allUserId);
        return notification;
    }

    @PostMapping(path = "/trimesters/{trimesterId}/profiles/{profileId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByDepartment(@PathVariable("trimesterId") String trimesterId,
                                                        @PathVariable("profileId") String profileId,
                                                        @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        trimesterId = sanitizeTrimesterId(trimesterId);
        profileId = sanitizeProfileId(profileId);

        String finalProfileId = profileId;

        Calendar calendar = new GregorianCalendar();
        calendar.set(2000 + Integer.parseInt(trimesterId.substring(1, 3)), APRIL, 1);
        List<String> allUserId = zeuzUsersByGroupClient.getUsers(calendar.getTime(), trimesterId, profileId)
                                                       .stream()
                                                       .filter(userByGroup -> finalProfileId.equals(
                                                               userByGroup.getProfileId()))
                                                       .map(UserByGroup::getUserId)
                                                       .distinct()
                                                       .collect(Collectors.toList());

        saveNotificationAndImportUserIfNotExist(notification, allUserId);
        return notification;
    }

    private void saveNotificationAndImportUserIfNotExist(Notification notification, List<String> allUserId)
    {
        List<User> allUser = userService.getAllUser(allUserId);

        allUserId.removeAll(allUser.stream().map(User::getId).collect(Collectors.toList()));
        allUser.addAll(userService.createAllUser(allUserId));

        notificationService.createAllNotificationForUsers(notification, allUser);

        sendNotificationForAllUsers(notification, allUser);
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
