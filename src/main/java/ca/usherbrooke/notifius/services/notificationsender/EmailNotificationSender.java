package ca.usherbrooke.notifius.services.notificationsender;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender extends NotificationSender
{
    @Autowired
    private EmailService emailService;

    @Override
    void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getEmailServiceEnable()) {
            emailService.sendEmail(user.getEmail(),
                                   notification.getTitle(),
                                   notification.getContent());
        }
    }
}
