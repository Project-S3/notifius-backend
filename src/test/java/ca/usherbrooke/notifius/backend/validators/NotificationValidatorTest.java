package ca.usherbrooke.notifius.backend.validators;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.resterrors.TitleTooLongException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static ca.usherbrooke.notifius.backend.models.Service.TEST;

@RunWith(SpringRunner.class)
public class NotificationValidatorTest
{
    private static final int MAX_LENGTH = 10;

    private NotificationValidator notificationValidator;

    @Before
    public void init() {
        notificationValidator = new NotificationValidator();
        notificationValidator.setMaxStringLength(MAX_LENGTH);
    }

    @Test(expected = TitleTooLongException.class)
    public void test() {
        Notification notification = new Notification().withTitle("A very long title nani")
                                                      .withContent("content")
                                                      .withDate(new Date())
                                                      .withService(TEST);
        notificationValidator.validNotificationThrowIfNotValid(notification);
    }
}