package ca.usherbrooke.notifius.backend.services.notificationsender;

import ca.usherbrooke.notifius.backend.services.SmsService;
import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.services.UserNotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationSender extends NotificationSender
{
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Override
    public void sendNotifications(Notification notification, User user)
    {
        String attribute = userNotificationSenderService.getAttributeIfExists(user.getId(),this.getNotificationSenderId());
        if (attribute != null)
        {
            smsService.sendSMS(attribute,
                              String.format("%s\n\n%s\n\nEnvoyé par Notifius",
                                            notification.getTitle(),
                                            notification.getContent()));
        }
    }

    @Override
    public String getNotificationSenderId()
    {
        return "SMS_SENDER";
    }
}
