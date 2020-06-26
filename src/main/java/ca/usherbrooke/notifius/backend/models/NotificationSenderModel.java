/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.models;

public class NotificationSenderModel
{
    private String id;
    private String displayName;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public NotificationSenderModel withId(String id)
    {
        setId(id);
        return this;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public NotificationSenderModel withDisplayName(String displayName)
    {
        setDisplayName(displayName);
        return this;
    }
}
