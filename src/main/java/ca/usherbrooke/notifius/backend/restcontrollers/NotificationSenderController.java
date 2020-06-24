package ca.usherbrooke.notifius.backend.restcontrollers;

import ca.usherbrooke.notifius.backend.services.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/notification-senders")
public class NotificationSenderController
{
    @Autowired
    private NotificationSenderService notificationSenderService;

    @GetMapping("")
    public Map<String, String> getAllNotificationSender()
    {
        return notificationSenderService.getAll();
    }
}
