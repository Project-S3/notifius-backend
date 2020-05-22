package ca.usherbrooke.notifius.restcontrollers;

import ca.usherbrooke.notifius.entities.NotificationEntity;
import ca.usherbrooke.notifius.entities.SettingsEntity;
import ca.usherbrooke.notifius.entities.UserEntity;
import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.repositories.NotificationRepository;
import ca.usherbrooke.notifius.repositories.SettingsRepository;
import ca.usherbrooke.notifius.repositories.UserRepository;
import ca.usherbrooke.notifius.services.EmailService;
import ca.usherbrooke.notifius.services.SmsService;
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
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private static final String USHERBROOKE_EMAIL_FORMAT = "%s@usherbrooke.ca";

    @Autowired
    private NotificationValidator notificationValidator;
    @Autowired
    private NotificationToEntityTranslator notificationToEntityTranslator;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SettingsRepository settingRepository;


    @GetMapping(path = "/users/{userId}/notifications",
                produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Notification> getNotifications(@PathVariable("userId") String userId,
                                               @RequestParam(value = "service", required = false) String serviceId,
                                               @RequestParam(value = "date", required = false) String date)
    {
        UserEntity user = new UserEntity("gram3405");
        SettingsEntity settings = new SettingsEntity(user);
        userRepository.save(user);
        settingRepository.save(settings);

        return new ArrayList<>();
    }

    @PostMapping(path = "/users/{userId}/notifications",
                 consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Notification createNotificationByUser(@PathVariable("userId") String userId,
                                                 @RequestBody Notification notification)
    {
        UserEntity userEntity = new UserEntity(userId);
        userRepository.save(userEntity);

        SettingsEntity settingsEntity = new SettingsEntity(userEntity);
        settingRepository.save(settingsEntity);




        notificationValidator.validNotificationThrowIfNotValid(notification);

        NotificationEntity notificationEntity = notificationToEntityTranslator.toEntity(notification).withUser(userEntity);
        notificationRepository.save(notificationEntity);

        emailService.sendEmail(String.format(USHERBROOKE_EMAIL_FORMAT, userId),
                              notification.getTitle(),
                               notification.getContent());

//        smsService.sendSMS("+18199051016", notification.getTitle()
//                                           + "\n\n" + notification.getContent()
//                                           + "\n\nEnvoyé par Notifius");



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

        NotificationEntity notificationEntity = notificationToEntityTranslator.toEntity(notification);
        notificationRepository.save(notificationEntity);

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

        NotificationEntity notificationEntity = notificationToEntityTranslator.toEntity(notification);
        notificationRepository.save(notificationEntity);

        return notification;
    }
}
