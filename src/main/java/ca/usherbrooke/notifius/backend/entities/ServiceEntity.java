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
    @Column
    private String displayName;
    @Column
    private String description;

    @ManyToMany(mappedBy = "enableServices")
    private Set<UserEntity> user;

    public ServiceEntity()
    {
    }

    public ServiceEntity(Service service)
    {
        this.id = service;
        this.displayName = service.getDisplayName();
        this.description = service.getDescription();
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

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public ServiceEntity withDisplayName(String displayName)
    {
        this.setDisplayName(displayName);
        return this;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ServiceEntity withDescription(String description)
    {
        this.setDescription(description);
        return this;
    }

    public String[] getValues()
    {
        return new String[] {getDisplayName(), getDescription()};
    }
}

