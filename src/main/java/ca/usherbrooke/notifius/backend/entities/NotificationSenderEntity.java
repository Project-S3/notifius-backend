package ca.usherbrooke.notifius.backend.entities;

import ca.usherbrooke.notifius.backend.models.Service;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "notification_sender")
public class NotificationSenderEntity
{
    @Id
    private String id;

    public NotificationSenderEntity()
    {

    }
    public NotificationSenderEntity(String id)
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

    public NotificationSenderEntity withId(String id)
    {
        this.setId(id);
        return this;
    }
}