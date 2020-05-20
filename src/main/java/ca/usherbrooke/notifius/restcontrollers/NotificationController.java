package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.validators.NotificationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController
{
    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationValidator notificationValidator;

    @GetMapping(value = "/users/{userId}/notifications",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Notification> getNotifications(@PathVariable("userId") String userId,
                                               @RequestParam(value = "service", required = false) String serviceId,
                                               @RequestParam(value = "date", required = false) String date)
    {
        return null;
    }

    @PostMapping(value = "/users/{userId}/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationByUser(@PathVariable("userId") String userId,
                                                 @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        return null;
    }

    @PostMapping(value = "/trimester/{trimesterId}/activities/{activityId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByActivity(@PathVariable("trimesterId") String trimesterId,
                                                      @PathVariable("activityId") String activityId,
                                                      @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        return null;
    }

    @PostMapping(value = "/departments/{departmentId}/sessions/{sessionId}/trimesters/{trimesterId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByDepartment(@PathVariable("departmentId") String departmentId,
                                                        @PathVariable("sessionId") String sessionId,
                                                        @PathVariable("trimesterId") String trimesterId,
                                                        @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);
        return null;
    }
}
