package ca.usherbrooke.notifius.entities;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "notifius_user")
public class UserEntity
{
    @Id
    private String id;
    @Column(nullable = false)
    private String email;
    @Column
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    private SettingsEntity settings;
    @OneToMany(mappedBy = "user")
    private Set<NotificationEntity> notifications;

    public UserEntity()
    {
    }

    public UserEntity(String id)
    {
        this.id = id;
        this.email = String.format("%s@usherbrooke.ca", id);
    }

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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public UserEntity withEmail(String email)
    {
        setEmail(email);
        return this;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public UserEntity withPhoneNumber(String phoneNumber)
    {
        setPhoneNumber(phoneNumber);
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
