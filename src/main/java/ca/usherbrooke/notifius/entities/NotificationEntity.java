package ca.usherbrooke.notifius.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "notification")
public class NotificationEntity
{
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    public NotificationEntity()
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

    public NotificationEntity withId(Long id)
    {
        setId(id);
        return this;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public NotificationEntity withTitle(String title)
    {
        setTitle(title);
        return this;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public NotificationEntity withContent(String content)
    {
        setContent(content);
        return this;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public NotificationEntity withDate(Date date)
    {
        setDate(date);
        return this;
    }

    public UserEntity getUser()
    {
        return user;
    }

    public void setUser(UserEntity user)
    {
        this.user = user;
    }

    public NotificationEntity withUser(UserEntity user)
    {
        setUser(user);
        return this;
    }
}
