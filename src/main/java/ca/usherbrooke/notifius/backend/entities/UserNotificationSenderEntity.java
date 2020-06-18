package ca.usherbrooke.notifius.backend.entities;

import javax.persistence.*;
import java.util.Optional;

@Entity(name = "user_notification_sender")
public class UserNotificationSenderEntity
{
    @EmbeddedId
    UserNotificationSenderKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @MapsId("notification_sender_id")
    @JoinColumn(name = "notification_sender_id", nullable = false)
    private NotificationSenderEntity notificationSender;

    @Column
    private String attribute;

    public UserEntity getUser()
    {
        return user;
    }

    public void setUser(UserEntity user)
    {
        this.user = user;
    }

    public UserNotificationSenderEntity withUser(UserEntity user)
    {
        this.setUser(user);
        return this;
    }

    public NotificationSenderEntity getNotificationSender()
    {
        return notificationSender;
    }

    public void setNotificationSender(NotificationSenderEntity notificationSender)
    {
        this.notificationSender = notificationSender;
    }

    public UserNotificationSenderEntity withNotificationSender(NotificationSenderEntity notificationSender)
    {
        this.setNotificationSender(notificationSender);
        return this;
    }

    public String getAttribute()
    {
        return attribute;
    }

    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }

    public UserNotificationSenderEntity withAttribute(String attribute)
    {
        this.setAttribute(attribute);
        return this;
    }

    public UserNotificationSenderKey getId()
    {
        return id;
    }

    public void setId(UserNotificationSenderKey id)
    {
        this.id = id;
    }

    public UserNotificationSenderEntity withId(UserNotificationSenderKey id)
    {
        this.setId(id);
        return this;
    }
}
