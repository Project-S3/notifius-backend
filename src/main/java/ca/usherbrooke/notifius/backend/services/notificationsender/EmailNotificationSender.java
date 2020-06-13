package ca.usherbrooke.notifius.backend.services.notificationsender;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.EmailService;
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
