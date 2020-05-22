package ca.usherbrooke.notifius.entities;

import ca.usherbrooke.notifius.models.Service;

import javax.persistence.*;

@Entity(name = "settings")
public class SettingsEntity
{
    @Id
    @GeneratedValue
    private Long id;
    public Boolean emailService = true;
    public Boolean smsService = false;

    public Boolean BOOK  = true;
    public Boolean SCHEDULE = true;
    public Boolean MENTORING = true;
    public Boolean EXAM  = true;
    public Boolean TUTORING  = true;
    public Boolean STEWARD  = true;
    public Boolean GRADE  = true;
    public Boolean MESSAGE  = true;
    public Boolean TEST = true;


    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public SettingsEntity(UserEntity user)
    {
        this.user = user;
    }
}
