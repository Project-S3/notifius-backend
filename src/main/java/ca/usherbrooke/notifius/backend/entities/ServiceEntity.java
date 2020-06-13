package ca.usherbrooke.notifius.backend.entities;

import ca.usherbrooke.notifius.backend.models.Service;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "service")
public class ServiceEntity
{
    @Id
    @Enumerated(EnumType.STRING)
    private Service id;

    @ManyToMany(mappedBy = "enableServices")
    private Set<SettingsEntity> settings;

    public ServiceEntity()
    {

    }
    public ServiceEntity(Service service)
    {
        this.id = service;
    }

    public Service getId()
    {
        return id;
    }

    public void setId(Service id)
    {
        this.id = id;
    }

    public ServiceEntity withId(Service id)
    {
        this.setId(id);
        return this;
    }
}
