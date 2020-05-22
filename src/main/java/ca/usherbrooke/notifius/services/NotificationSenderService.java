package ca.usherbrooke.notifius.services;

import ca.usherbrooke.notifius.entities.NotificationEntity;
import ca.usherbrooke.notifius.entities.UserEntity;
import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.repositories.NotificationRepository;
import ca.usherbrooke.notifius.resterrors.UserNotFoundException;
import ca.usherbrooke.notifius.translators.NotificationToEntityTranslator;
import ca.usherbrooke.notifius.validators.NotificationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderService
{
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationValidator notificationValidator;
    @Autowired
    private NotificationToEntityTranslator notificationToEntityTranslator;
    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotifications(Notification notification, String userId)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        UserEntity userEntity = userService.getUser(userId).orElseThrow(UserNotFoundException::new);


        NotificationEntity notificationEntity = notificationToEntityTranslator.toEntity(notification).withUser(userEntity);
        notificationRepository.save(notificationEntity);

        if(userEntity.getSettings().getEmailService())
        {
            emailService.sendEmail(userEntity.getEmail(),
                                   notification.getTitle(),
                                   notification.getContent());
        }
        if(userEntity.getSettings().getSmsService())
        {
            smsService.sendSMS(userEntity.getPhoneNumber(),
                               String.format("%s\n\n%s\n\nEnvoyé par Notifius",
                                             notification.getTitle(),
                                             notification.getContent()) );
        }

    }

}
