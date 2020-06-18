package ca.usherbrooke.notifius.backend.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Settings
{
    private Long id;
    private Set<Service> enableServices = new HashSet<>();

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Settings withId(Long id)
    {
        setId(id);
        return this;
    }

    public Set<Service> getEnableServices()
    {
        return enableServices;
    }

    public void setEnableServices(Set<Service> enableServices)
    {
        this.enableServices = enableServices;
    }

    public Settings withEnableServices(Set<Service> enableServices)
    {
        this.setEnableServices(enableServices);
        return this;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        if (!Objects.equals(id, settings.id)) return false;
        return Objects.equals(enableServices, settings.enableServices);
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enableServices != null ? enableServices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Settings{" +
               "id=" + id +
               ", enableServices=" + enableServices +
               '}';
    }
}
