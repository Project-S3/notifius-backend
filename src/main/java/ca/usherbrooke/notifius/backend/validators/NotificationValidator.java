/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.validators;

import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.resterrors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class NotificationValidator
{
    @Value("${notifius.request.max-notification-title-length}")
    private Integer maxNotificationTileLength;

    @Value("${notifius.request.max-notification-content-length}")
    private Integer maxNotificationContentLength;

    public void validNotificationThrowIfNotValid(Notification notification)
    {
        if (!StringUtils.hasText(notification.getTitle()))
            throw new TitleEmptyException();

        if (notification.getTitle().length() > maxNotificationTileLength)
            throw new TitleTooLongException();

        if (!StringUtils.hasText(notification.getContent()))
            throw new ContentEmptyException();

        if (notification.getContent().length() > maxNotificationContentLength)
            throw new ContentTooLongException();

        if (notification.getDate().compareTo(new Date()) > 0)
            throw new DateInvalidException();
    }

    public Integer getMaxNotificationTileLength()
    {
        return maxNotificationTileLength;
    }

    public void setMaxNotificationTileLength(Integer maxNotificationTileLength)
    {
        this.maxNotificationTileLength = maxNotificationTileLength;
    }

    public Integer getMaxNotificationContentLength()
    {
        return maxNotificationContentLength;
    }

    public void setMaxNotificationContentLength(Integer maxNotificationContentLength)
    {
        this.maxNotificationContentLength = maxNotificationContentLength;
    }
}
