/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.validators;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.resterrors.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static ca.usherbrooke.notifius.backend.models.Service.TEST;
import static java.util.Calendar.HOUR_OF_DAY;

@RunWith(SpringRunner.class)
public class NotificationValidatorTest
{
    private static final int MAX_LENGTH = 10;

    private NotificationValidator notificationValidator;
    private Notification notification;

    @Before
    public void init()
    {
        notificationValidator = new NotificationValidator();
        notificationValidator.setMaxNotificationTileLength(MAX_LENGTH);
        notificationValidator.setMaxNotificationContentLength(MAX_LENGTH);
        notification = new Notification().withTitle("title")
                                         .withContent("content")
                                         .withDate(new Date())
                                         .withService(TEST);
    }

    @Test(expected = TitleEmptyException.class)
    public void testNotificationTitleEmpty()
    {
        notificationValidator.validNotificationThrowIfNotValid(notification.withTitle(""));
    }

    @Test(expected = TitleEmptyException.class)
    public void testNotificationTitleNull()
    {
        notificationValidator.validNotificationThrowIfNotValid(notification.withTitle(null));
    }

    @Test(expected = TitleTooLongException.class)
    public void testNotificationTitleTooLong()
    {
        notificationValidator.validNotificationThrowIfNotValid(notification.withTitle("Very long title bla bla bla"));
    }

    @Test(expected = ContentEmptyException.class)
    public void testNotificationContentEmpty()
    {
        notificationValidator.validNotificationThrowIfNotValid(notification.withContent(""));
    }

    @Test(expected = ContentEmptyException.class)
    public void testNotificationContentNull()
    {
        notificationValidator.validNotificationThrowIfNotValid(notification.withContent(null));
    }

    @Test(expected = ContentTooLongException.class)
    public void testNotificationContentTooLong()
    {
        notificationValidator.validNotificationThrowIfNotValid(
                notification.withContent("A very long content Muda! Muda! Muda! Muda! Muda! Muda! "));
    }

    @Test(expected = DateInvalidException.class)
    public void testNotificationDateInvalid()
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(HOUR_OF_DAY, 1);
        notificationValidator.validNotificationThrowIfNotValid(notification.withDate(c.getTime()));
    }
}