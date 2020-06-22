package ca.usherbrooke.notifius.backend.models;

import java.util.*;

public class Settings
{
    private Set<Service> enableServices = new HashSet<>();
    private Map<String, String> notificationSenders = new HashMap<>();

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
        setEnableServices(enableServices);
        return this;
    }

    public Map<String, String> getNotificationSenders()
    {
        return notificationSenders;
    }

    public void setNotificationSenders(Map<String, String> notificationSenders)
    {
        this.notificationSenders = notificationSenders;
    }

    public Settings withNotificationSenders(Map<String, String> notificationSenders)
    {
        setNotificationSenders(notificationSenders);
        return this;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        if (!Objects.equals(enableServices, settings.enableServices))
            return false;
        return Objects.equals(notificationSenders, settings.notificationSenders);
    }

    @Override
    public int hashCode()
    {
        int result = enableServices != null ? enableServices.hashCode() : 0;
        result = 31 * result + (notificationSenders != null ? notificationSenders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Settings{" +
               "enableServices=" + enableServices +
               ", notificationSenders=" + notificationSenders +
               '}';
    }
}
