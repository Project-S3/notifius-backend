package ca.usherbrooke.notifius.services.notificationsender;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import ca.usherbrooke.notifius.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationSender extends NotificationSender
{
    @Autowired
    private SmsService smsService;

    @Override
    void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getSmsServiceEnable()) {
            smsService.sendSMS(user.getPhoneNumber(),
                               String.format("%s\n\n%s\n\nEnvoyé par Notifius",
                                             notification.getTitle(),
                                             notification.getContent()));
        }
    }
}
