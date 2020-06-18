package ca.usherbrooke.notifius.backend.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "settings")
public class SettingsEntity
{
    @Id
    @GeneratedValue
    private Long id;

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
