package ca.usherbrooke.notifius.backend.entities;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "notifius_user")
public class UserEntity
{
    @Id
    private String id;

    @OneToMany(mappedBy = "user")
    private Set<NotificationEntity> notifications;

    @OneToMany(mappedBy = "user")
    Set<UserNotificationSenderEntity> userNotificationSender;

    @ManyToMany
    @JoinTable(
            name = "user_services",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<ServiceEntity> enableServices;

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

    public Set<ServiceEntity> getEnableServices()
    {
        return enableServices;
    }

    public void setEnableServices(Set<ServiceEntity> enableServices)
    {
        this.enableServices = enableServices;
    }

    public UserEntity withEnableServices(Set<ServiceEntity> enableServices)
    {
        this.setEnableServices(enableServices);
        return this;
    }
}

