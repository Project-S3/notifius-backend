package ca.usherbrooke.notifius.validators;

import ca.usherbrooke.notifius.models.Notification;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

import static ca.usherbrooke.notifius.models.Service.TEST;
import static org.junit.Assert.assertTrue;

class NotificationValidatorTest
{
    private static final Integer MAX_STRING_LENGTH = 10;

    private final NotificationValidator notificationValidator = new NotificationValidator()
            .withMaxStringLength(MAX_STRING_LENGTH);

    private Notification notificationOk;

    @BeforeEach
    public void setUp()
    {
        notificationOk = new Notification().withTitle("Tile")
                                           .withContent("content")
                                           .withDate(new Date())
                                           .withService(TEST);
    }

    @Test
    public void validNotificationOk()
    {
        boolean isOK = notificationValidator.validNotification(notificationOk);

        assertTrue(isOK);

    }

    @Test
    public void validNotificationTitleTooLong()
    {

    }
}