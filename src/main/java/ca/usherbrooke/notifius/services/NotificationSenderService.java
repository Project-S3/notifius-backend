package ca.usherbrooke.notifius.services;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.resterrors.UserNotFoundException;
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
    private NotificationService notificationService;
    @Autowired
    private NotificationValidator notificationValidator;

    // TODO METTRE DES LOGS
    public void sendNotifications(Notification notification, String userId)
    {
        notificationValidator.validNotificationThrowIfNotValid(notification);

        User user = userService.getUser(userId).orElseThrow(UserNotFoundException::new);

        if (user.getSettings().getEnableServices().contains(notification.getService()))
        {
            notificationService.saveNotification(notification, userId);

            if (user.getSettings().getEmailServiceEnable()) {
                emailService.sendEmail(user.getEmail(),
                                       notification.getTitle(),
                                       notification.getContent());
            }
            //toto valid settings
            if (user.getSettings().getSmsServiceEnable()) {
                smsService.sendSMS(user.getPhoneNumber(),
                                   String.format("%s\n\n%s\n\nEnvoyé par Notifius",
                                                 notification.getTitle(),
                                                 notification.getContent()));
            }
        }
    }
}
