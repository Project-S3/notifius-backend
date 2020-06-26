/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "notification_sender")
public class NotificationSenderEntity
{
    @OneToMany(mappedBy = "notificationSender")
    Set<UserNotificationSenderEntity> userNotificationSender;
    @Id
    private String id;
    @Column
    private String displayName;

    public NotificationSenderEntity()
    {
    }

    public NotificationSenderEntity(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public NotificationSenderEntity withId(String id)
    {
        this.setId(id);
        return this;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public NotificationSenderEntity withDisplayName(String displayName)
    {
        this.setDisplayName(displayName);
        return this;
    }

    public Set<UserNotificationSenderEntity> getUserNotificationSender()
    {
        return userNotificationSender;
    }

    public void setUserNotificationSender(Set<UserNotificationSenderEntity> userNotificationSender)
    {
        this.userNotificationSender = userNotificationSender;
    }
}