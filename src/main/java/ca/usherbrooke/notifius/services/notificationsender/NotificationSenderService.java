package ca.usherbrooke.notifius.services.notificationsender;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.services.EmailService;
import ca.usherbrooke.notifius.services.SmsService;
import ca.usherbrooke.notifius.services.UserService;
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

    // TODO METTRE DES LOGS
    public void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getEnableServices().contains(notification.getService()))
        {
            if (user.getSettings().getEmailServiceEnable()) {
                emailService.sendEmail(user.getEmail(),
                                       notification.getTitle(),
                                       notification.getContent());
            }
            if (user.getSettings().getSmsServiceEnable()) {
                smsService.sendSMS(user.getPhoneNumber(),
                                   String.format("%s\n\n%s\n\nEnvoyé par Notifius",
                                                 notification.getTitle(),
                                                 notification.getContent()));
            }
        }
    }
}
