package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.models.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController
{
    @GetMapping("/users/{userId}/notifications")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Notification> getNotifications(@PathVariable("userId") String userId)
    {
        return null;
    }

    @PostMapping("/users/{userId}/notifications")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationByUser(@PathVariable("userId") String userId)
    {
        return null;
    }

    @PostMapping("/trimester/{trimesterId}/activities/{activityId}/users/notifications")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByActivity(@PathVariable("trimesterId") String trimesterId,
                                                      @PathVariable("activityId") String activityId)
    {
        return null;
    }

    @PostMapping("/departments/{departmentId}/sessions/{sessionId}/trimesters/{trimesterId}/users/notifications")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByDepartment(@PathVariable("departmentId") String departmentId,
                                                        @PathVariable("sessionId") String sessionId,
                                                        @PathVariable("trimesterId") String trimesterId)
    {
        return null;
    }
}
