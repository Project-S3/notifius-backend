package ca.usherbrooke.notifius.entities;

import javax.persistence.*;

@Entity(name = "settings")
public class SettingEntity
{
    @Id
    @GeneratedValue
    private Long id;


    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public SettingEntity(UserEntity user)
    {
        this.user = user;
    }
}
