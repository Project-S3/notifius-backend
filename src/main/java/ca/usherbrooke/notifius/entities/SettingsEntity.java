package ca.usherbrooke.notifius.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "settings")
public class SettingsEntity
{
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Boolean emailService = true;
    @Column
    private Boolean smsService = false;

    @ManyToMany
    @JoinTable(
            name = "settings_services",
            joinColumns = @JoinColumn(name = "settings_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<ServiceEntity> enableServices = new HashSet<>();



    public SettingsEntity()
    {

    }

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

    public Boolean getEmailService()
    {
        return emailService;
    }

    public void setEmailService(Boolean emailService)
    {
        this.emailService = emailService;
    }

    public SettingsEntity withEmailService(Boolean emailService)
    {
        setEmailService(emailService);
        return this;
    }

    public Boolean getSmsService()
    {
        return smsService;
    }

    public void setSmsService(Boolean smsService)
    {
        this.smsService = smsService;
    }

    public SettingsEntity withSmsService(Boolean smsService)
    {
        setSmsService(smsService);
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
