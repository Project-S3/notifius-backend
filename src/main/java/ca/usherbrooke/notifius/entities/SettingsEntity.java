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

    public SettingsEntity withSmsService(Boolean smsService)
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

    public SettingsEntity withBook(Boolean book)
    {
        setBook(book);
        return this;
    }

    public Boolean getSchedule()
    {
        return schedule;
    }

    public void setSchedule(Boolean schedule)
    {
        this.schedule = schedule;
    }

    public SettingsEntity withSchedule(Boolean schedule)
    {
        setSchedule(schedule);
        return this;
    }

    public Boolean getMentoring()
    {
        return mentoring;
    }

    public void setMentoring(Boolean mentoring)
    {
        this.mentoring = mentoring;
    }

    public SettingsEntity withMentoring(Boolean mentoring)
    {
        setMentoring(mentoring);
        return this;
    }

    public Boolean getExam()
    {
        return exam;
    }

    public void setExam(Boolean exam)
    {
        this.exam = exam;
    }

    public SettingsEntity withExam(Boolean exam)
    {
        setExam(exam);
        return this;
    }

    public Boolean getTutoring()
    {
        return tutoring;
    }

    public void setTutoring(Boolean tutoring)
    {
        this.tutoring = tutoring;
    }

    public SettingsEntity withTutoring(Boolean tutoring)
    {
        setTutoring(tutoring);
        return this;
    }

    public Boolean getSteward()
    {
        return steward;
    }

    public void setSteward(Boolean steward)
    {
        this.steward = steward;
    }

    public SettingsEntity withSteward(Boolean steward)
    {
        setSteward(steward);
        return this;
    }

    public Boolean getGrade()
    {
        return grade;
    }

    public void setGrade(Boolean grade)
    {
        this.grade = grade;
    }

    public SettingsEntity withGrade(Boolean grade)
    {
        setGrade(grade);
        return this;
    }

    public Boolean getMessage()
    {
        return message;
    }

    public void setMessage(Boolean message)
    {
        this.message = message;
    }

    public SettingsEntity withMessage(Boolean message)
    {
        setMessage(message);
        return this;
    }
}
