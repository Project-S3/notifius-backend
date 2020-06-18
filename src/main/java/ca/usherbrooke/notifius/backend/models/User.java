package ca.usherbrooke.notifius.backend.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User
{
    private String id;
       private Settings settings;
    private Set<Notification> notifications = new HashSet<>();

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public User withId(String id)
    {
        setId(id);
        return this;
    }

    public Settings getSettings()
    {
        return settings;
    }

    public void setSettings(Settings settings)
    {
        this.settings = settings;
    }

    public User withSettings(Settings settings)
    {
        setSettings(settings);
        return this;
    }

    public Set<Notification> getNotifications()
    {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications)
    {
        this.notifications = notifications;
    }

    public User withNotifications(Set<Notification> notifications)
    {
        setNotifications(notifications);
        return this;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(settings, user.settings)) return false;
        return Objects.equals(notifications, user.notifications);
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (settings != null ? settings.hashCode() : 0);
        result = 31 * result + (notifications != null ? notifications.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "User{" +
               "id='" + id + '\'' +
               ", settings=" + settings +
               ", notifications=" + notifications +
               '}';
    }
}
