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

    public SettingsEntity()
    {
    }

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

    public Boolean getEmailService()
    {
        return emailService;
    }

    public void setEmailService(Boolean emailService)
    {
        this.emailService = emailService;
    }

    public SettingsEntity withEmailService(Boolean emailService)
    {
        setEmailService(emailService);
        return this;
    }

    public Boolean getSmsService()
    {
        return smsService;
    }

    public void setSmsService(Boolean smsService)
    {
        this.smsService = smsService;
    }

    public SettingsEntity withSmsService(boolean smsService)
    {
        setSmsService(smsService);
        return this;
    }

    public Boolean getBook()
    {
        return book;
    }

    public void setBook(Boolean book)
    {
        this.book = book;
    }

    public Boolean getSchedule()
    {
        return schedule;
    }

    public void setSchedule(Boolean schedule)
    {
        this.schedule = schedule;
    }

    public Boolean getMentoring()
    {
        return mentoring;
    }

    public void setMentoring(Boolean mentoring)
    {
        this.mentoring = mentoring;
    }

    public Boolean getExam()
    {
        return exam;
    }

    public void setExam(Boolean exam)
    {
        this.exam = exam;
    }

    public Boolean getTutoring()
    {
        return tutoring;
    }

    public void setTutoring(Boolean tutoring)
    {
        this.tutoring = tutoring;
    }

    public Boolean getSteward()
    {
        return steward;
    }

    public void setSteward(Boolean steward)
    {
        this.steward = steward;
    }

    public Boolean getGrade()
    {
        return grade;
    }

    public void setGrade(Boolean grade)
    {
        this.grade = grade;
    }

    public Boolean getMessage()
    {
        return message;
    }

    public void setMessage(Boolean message)
    {
        this.message = message;
    }
}
