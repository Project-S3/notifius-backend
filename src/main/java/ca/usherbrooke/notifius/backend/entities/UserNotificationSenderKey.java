/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserNotificationSenderKey implements Serializable
{
    @Column(name = "user_id")
    String userId;
    @Column(name = "notification_sender_id")
    String notificationSenderId;

    public UserNotificationSenderKey()
    {
    }

    public UserNotificationSenderKey(String userId, String notificationSenderId)
    {
        this.userId = userId;
        this.notificationSenderId = notificationSenderId;
    }
}