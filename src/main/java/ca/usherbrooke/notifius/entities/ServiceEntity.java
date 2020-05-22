package ca.usherbrooke.notifius.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "service")
public class ServiceEntity
{
    @Id
    private String id;
    @Column
    private String description;

//    @ManyToMany(mappedBy = "enableServices")
//    Set<SettingsEntity> settings;

    public ServiceEntity()
    {

    }
    public ServiceEntity(String id, String description)
    {
        this.id = id;
        this.description = description;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public ServiceEntity withId(String id)
    {
        this.setId(id);
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
}
