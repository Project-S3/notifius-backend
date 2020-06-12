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
    @Column
    private String customUrl;
    @Column
    private String discordWebhookUrl;
    @Column
    private String teamsWebhookUrl;

    @OneToOne(cascade = CascadeType.DETACH)
    private SettingsEntity settings;
    @OneToMany(mappedBy = "user")
    private Set<NotificationEntity> notifications;

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

    public String getCustomUrl()
    {
        return customUrl;
    }

    public void setCustomUrl(String customUrl)
    {
        this.customUrl = customUrl;
    }

    public String getDiscordWebhookUrl()
    {
        return discordWebhookUrl;
    }

    public void setDiscordWebhookUrl(String discordWebhookUrl)
    {
        this.discordWebhookUrl = discordWebhookUrl;
    }

    public UserEntity withDiscordWebhookUrl(String discordWebhookUrl)
    {
        setDiscordWebhookUrl(discordWebhookUrl);
        return this;
    }

    public String getTeamsWebhookUrl()
    {
        return teamsWebhookUrl;
    }

    public void setTeamsWebhookUrl(String discordWebhookUrl)
    {
        this.teamsWebhookUrl = teamsWebhookUrl;
    }

    public UserEntity withTeamsWebhookUrl(String teamsWebhookUrl)
    {
        setTeamsWebhookUrl(teamsWebhookUrl);
        return this;
    }

    public UserEntity withCustomUrl(String customUrl) {
        setCustomUrl(customUrl);
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
