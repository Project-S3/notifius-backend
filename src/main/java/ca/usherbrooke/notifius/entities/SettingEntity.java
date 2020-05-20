package ca.usherbrooke.notifius.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "settings")
public class SettingEntity
{
    @Id
    private Long id;
}
