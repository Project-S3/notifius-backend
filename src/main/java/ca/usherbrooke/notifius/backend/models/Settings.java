package ca.usherbrooke.notifius.backend.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Settings
{
    private Long id;
    private Boolean emailServiceEnable;
    private Boolean smsServiceEnable;
    private Boolean httpServiceEnable;
    private Boolean discordWebhookEnable;
    private Boolean teamsWebhookEnable;
    private Boolean slackWebhookEnable;
    private Set<Service> enableServices = new HashSet<>();

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Settings withId(Long id)
    {
        setId(id);
        return this;
    }

    public Boolean getEmailServiceEnable()
    {
        return emailServiceEnable;
    }

    public void setEmailServiceEnable(Boolean emailServiceEnable)
    {
        this.emailServiceEnable = emailServiceEnable;
    }

    public Settings withEmailServiceEnable(Boolean emailService)
    {
        setEmailServiceEnable(emailService);
        return this;
    }

    public Boolean getSmsServiceEnable()
    {
        return smsServiceEnable;
    }

    public void setSmsServiceEnable(Boolean smsServiceEnable)
    {
        this.smsServiceEnable = smsServiceEnable;
    }

    public Boolean getHttpServiceEnable()
    {
        return httpServiceEnable;
    }

    public void setHttpServiceEnable(Boolean httpServiceEnable)
    {
        this.httpServiceEnable = httpServiceEnable;
    }

    public Settings withHttpServiceEnable(Boolean httpServiceEnable)
    {
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

    public Settings withDiscordWebhookEnable(Boolean discordWebhookEnable)
    {
        setDiscordWebhookEnable(discordWebhookEnable);
        return this;
    }

    public Boolean getTeamsWebhookEnable()
    {
        return teamsWebhookEnable;
    }

    public void setTeamsWebhookEnable(Boolean teamsWebhookEnable)
    {
        this.teamsWebhookEnable = teamsWebhookEnable;
    }

    public Settings withTeamsWebhookEnable(Boolean teamsWebhookEnable)
    {
        setTeamsWebhookEnable(teamsWebhookEnable);
        return this;
    }

    public Boolean getSlackWebhookEnable()
    {
        return slackWebhookEnable;
    }

    public void setSlackWebhookEnable(Boolean slackWebhookEnable)
    {
        this.slackWebhookEnable = slackWebhookEnable;
    }

    public Settings withSlackWebhookEnable(Boolean slackWebhookEnable)
    {
        setSlackWebhookEnable(slackWebhookEnable);
        return this;
    }

    public Settings withSmsServiceEnable(Boolean smsService)
    {
        setSmsServiceEnable(smsService);
        return this;
    }

    public Set<Service> getEnableServices()
    {
        return enableServices;
    }

    public void setEnableServices(Set<Service> enableServices)
    {
        this.enableServices = enableServices;
    }

    public Settings withEnableServices(Set<Service> enableServices)
    {
        this.setEnableServices(enableServices);
        return this;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        if (!Objects.equals(id, settings.id)) return false;
        if (!Objects.equals(emailServiceEnable, settings.emailServiceEnable)) return false;
        if (!Objects.equals(smsServiceEnable, settings.smsServiceEnable)) return false;
        if (!Objects.equals(httpServiceEnable, settings.httpServiceEnable)) return false;
        return Objects.equals(enableServices, settings.enableServices);
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (emailServiceEnable != null ? emailServiceEnable.hashCode() : 0);
        result = 31 * result + (smsServiceEnable != null ? smsServiceEnable.hashCode() : 0);
        result = 31 * result + (httpServiceEnable != null ? httpServiceEnable.hashCode() : 0);
        result = 31 * result + (enableServices != null ? enableServices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Settings{" +
               "id=" + id +
               ", emailService=" + emailServiceEnable +
               ", smsService=" + smsServiceEnable +
               ", enableServices=" + enableServices +
               '}';
    }
}
