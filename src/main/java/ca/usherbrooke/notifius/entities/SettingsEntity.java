package ca.usherbrooke.notifius.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "settings")
public class SettingsEntity
{
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Boolean emailServiceEnable;
    @Column
    private Boolean smsServiceEnable;
    @Column
    private Boolean httpServiceEnable;
    @Column
    private Boolean discordWebhookEnable;

    @ManyToMany
    @JoinTable(
            name = "settings_services",
            joinColumns = @JoinColumn(name = "settings_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<ServiceEntity> enableServices;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public SettingsEntity withId(Long id)
    {
        setId(id);
        return this;
    }

    public Boolean getEmailServiceEnable()
    {
        return emailServiceEnable;
    }

    public void setEmailServiceEnable(Boolean emailService)
    {
        this.emailServiceEnable = emailService;
    }

    public SettingsEntity withEmailServiceEnable(Boolean emailService)
    {
        setEmailServiceEnable(emailService);
        return this;
    }

    public Boolean getSmsServiceEnable()
    {
        return smsServiceEnable;
    }

    public void setSmsServiceEnable(Boolean smsService)
    {
        this.smsServiceEnable = smsService;
    }

    public SettingsEntity withSmsServiceEnable(Boolean smsService)
    {
        setSmsServiceEnable(smsService);
        return this;
    }

    public Boolean getHttpServiceEnable()
    {
        return httpServiceEnable;
    }

    public void setHttpServiceEnable(Boolean httpServiceEnable)
    {
        this.httpServiceEnable = httpServiceEnable;
    }

    public SettingsEntity withHttpServiceEnable(Boolean httpServiceEnable) {
        setHttpServiceEnable(httpServiceEnable);
        return this;
    }

    public Boolean getDiscordWebhookEnable()
    {
        return discordWebhookEnable;
    }

    public void setDiscordWebhookEnable(Boolean discordWebhookEnable)
    {
        this.discordWebhookEnable = discordWebhookEnable;
    }

    public SettingsEntity withDiscordWebhookEnable(Boolean discordWebhookEnable)
    {
        setDiscordWebhookEnable(discordWebhookEnable);
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

    public SettingsEntity withEnableServices(Set<ServiceEntity> enableServices)
    {
        this.setEnableServices(enableServices);
        return this;
    }
}
