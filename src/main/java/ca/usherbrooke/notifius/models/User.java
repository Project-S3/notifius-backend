package ca.usherbrooke.notifius.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User
{
    private String id;
    private String email;
    private String phoneNumber;
    private String customUrl;
    private String discordWebhookUrl;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public User withEmail(String email)
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

    public String getCustomUrl()
    {
        return customUrl;
    }

    public void setCustomUrl(String customUrl)
    {
        this.customUrl = customUrl;
    }

    public User withCustomUrl(String customUrl) {
        setCustomUrl(customUrl);
        return this;
    }

    public String getDiscordWebhookUrl()
    {
        return discordWebhookUrl;
    }

    public void setDiscordWebhookUrl(String discordWebhookUrl)
    {
        this.discordWebhookUrl = discordWebhookUrl;
    }

    public User withDiscordWebhookUrl(String discordWebhookUrl)
    {
        setDiscordWebhookUrl(discordWebhookUrl);
        return this;
    }

    public User withPhoneNumber(String phoneNumber)
    {
        setPhoneNumber(phoneNumber);
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
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(phoneNumber, user.phoneNumber)) return false;
        if (!Objects.equals(customUrl, user.customUrl)) return false;
        if (!Objects.equals(discordWebhookUrl, user.discordWebhookUrl)) return false;
        if (!Objects.equals(settings, user.settings)) return false;
        return Objects.equals(notifications, user.notifications);
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (customUrl != null ? customUrl.hashCode() : 0);
        result = 31 * result + (discordWebhookUrl != null ? discordWebhookUrl.hashCode() : 0);
        result = 31 * result + (settings != null ? settings.hashCode() : 0);
        result = 31 * result + (notifications != null ? notifications.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "User{" +
               "id='" + id + '\'' +
               ", email='" + email + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", customUrl='" + customUrl + '\'' +
               ", discordWebhookUrl='" + discordWebhookUrl + '\'' +
               ", settings=" + settings +
               ", notifications=" + notifications +
               '}';
    }
}
