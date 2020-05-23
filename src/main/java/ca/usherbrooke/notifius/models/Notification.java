package ca.usherbrooke.notifius.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Notification implements Serializable
{
    private String title;
    private String content;
    private Date date;
    private Service service;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Notification withTitle(String title)
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

    public Notification withContent(String content)
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

    public Notification withDate(Date date)
    {
        setDate(date);
        return this;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public Notification withService(Service service)
    {
        setService(service);
        return this;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(content, that.content)) return false;
        if (!Objects.equals(date, that.date)) return false;
        return service == that.service;
    }

    @Override
    public int hashCode()
    {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Notification{" +
               "title='" + title + '\'' +
               ", content='" + content + '\'' +
               ", date=" + date +
               ", service=" + service +
               '}';
    }
}
