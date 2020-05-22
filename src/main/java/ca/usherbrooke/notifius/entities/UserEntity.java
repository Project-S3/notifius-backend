package ca.usherbrooke.notifius.entities;

import ca.usherbrooke.notifius.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;





@Entity(name = "notifius_user")
public class UserEntity
{
    @Id
    @Column(unique = true)
    private String id;

    @OneToOne(mappedBy = "user")
    private SettingEntity setting;

    @OneToMany(mappedBy="user")
    private Set<NotificationEntity> notifications;

    public UserEntity()
    {

    }

    public UserEntity(String id)
    {
        this.id = id;

    }

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
}
