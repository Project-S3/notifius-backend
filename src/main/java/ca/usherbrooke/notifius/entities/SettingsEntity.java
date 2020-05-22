package ca.usherbrooke.notifius.entities;

import javax.persistence.*;

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

    @Column
    private Boolean book = true;
    @Column
    private Boolean schedule = true;
    @Column
    private Boolean mentoring = true;
    @Column
    private Boolean exam = true;
    @Column
    private Boolean tutoring = true;
    @Column
    private Boolean steward = true;
    @Column
    private Boolean grade = true;
    @Column
    private Boolean message = true;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public SettingsEntity()
    {
    }

    public SettingsEntity(UserEntity user)
    {
        this.user = user;
    }
}
