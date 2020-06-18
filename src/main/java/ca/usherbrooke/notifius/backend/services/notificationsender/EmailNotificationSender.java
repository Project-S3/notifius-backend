package ca.usherbrooke.notifius.backend.services.notificationsender;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.repositories.NotificationSenderRepository;
import ca.usherbrooke.notifius.backend.services.EmailService;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender extends NotificationSender
{
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        String attribute = userNotificationSenderService.getAttributeIfExists(user.getId(),this.getNotificationSenderId());
        if (attribute != null)
        {
            emailService.sendEmail(attribute,
                                   notification.getTitle(),
                                   notification.getContent());
        }
    }

    @Override
    public String getNotificationSenderId()
    {
        return "EMAIL_SENDER";
    }
}
