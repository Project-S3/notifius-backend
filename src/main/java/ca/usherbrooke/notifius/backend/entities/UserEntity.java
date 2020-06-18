package ca.usherbrooke.notifius.backend.entities;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "notifius_user")
public class UserEntity
{
    @Id
    private String id;

    @OneToOne(cascade = CascadeType.DETACH)
    private SettingsEntity settings;
    @OneToMany(mappedBy = "user")
    private Set<NotificationEntity> notifications;

    @OneToMany(mappedBy = "user")
    Set<UserNotificationSenderEntity> userNotificationSender;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public UserEntity withId(String id)
    {
        setId(id);
        return this;
    }

    public SettingsEntity getSettings()
    {
        return settings;
    }

    public void setSettings(SettingsEntity settings)
    {
        this.settings = settings;
    }

    public UserEntity withSettings(SettingsEntity settings)
    {
        setSettings(settings);
        return this;
    }

    public Set<NotificationEntity> getNotifications()
    {
        return notifications;
    }

    public void setNotifications(Set<NotificationEntity> notifications)
    {
        this.notifications = notifications;
    }

    public UserEntity withNotifications(Set<NotificationEntity> notifications) {
        setNotifications(notifications);
        return this;
    }
}
