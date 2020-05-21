package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.entities.NotificationEntity;
import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.repositories.NotificationRepository;
import ca.usherbrooke.notifius.services.EmailService;
import ca.usherbrooke.notifius.translators.NotificationToEntityTranslator;
import ca.usherbrooke.notifius.validators.NotificationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotificationController
{
    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationValidator notificationValidator;
    @Autowired
    private NotificationToEntityTranslator notificationToEntityTranslator;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/users/{userId}/notifications",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Notification> getNotifications(@PathVariable("userId") String userId,
                                               @RequestParam(value = "service", required = false) String serviceId,
                                               @RequestParam(value = "date", required = false) String date)
    {
        return new ArrayList<>();
    }

    @PostMapping(value = "/users/{userId}/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationByUser(@PathVariable("userId") String userId,
                                                 @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        NotificationEntity notificationEntity = notificationToEntityTranslator.toEntity(notification);
        notificationRepository.save(notificationEntity);

        emailService.sendEmail(userId+"@usherbrooke.ca",
                               notification.getTitle(),
                               notification.getContent());

        return notification;
    }

    @PostMapping(value = "/trimester/{trimesterId}/activities/{activityId}/users/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationsByActivity(@PathVariable("trimesterId") String trimesterId,
                                                      @PathVariable("activityId") String activityId,
                                                      @RequestBody Notification notification)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        NotificationEntity notificationEntity = notificationToEntityTranslator.toEntity(notification);
        notificationRepository.save(notificationEntity);

        return notification;
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

        NotificationEntity notificationEntity = notificationToEntityTranslator.toEntity(notification);
        notificationRepository.save(notificationEntity);

        return notification;
    }
}
