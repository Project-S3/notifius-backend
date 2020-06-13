package ca.usherbrooke.notifius.backend.zeuz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class News
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("createur")
    private String creatorName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("unite")
    private String unit;
    @JsonProperty("cip")
    private String userId;
    @JsonProperty("inscription")
    private String inscription;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCreatorName()
    {
        return creatorName;
    }

    public void setCreatorName(String creatorName)
    {
        this.creatorName = creatorName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getInscription()
    {
        return inscription;
    }

    public void setInscription(String inscription)
    {
        this.inscription = inscription;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (!Objects.equals(id, news.id)) return false;
        if (!Objects.equals(creatorName, news.creatorName)) return false;
        if (!Objects.equals(description, news.description)) return false;
        if (!Objects.equals(unit, news.unit)) return false;
        if (!Objects.equals(userId, news.userId)) return false;
        return Objects.equals(inscription, news.inscription);
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (inscription != null ? inscription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "News{" +
               "id='" + id + '\'' +
               ", creatorName='" + creatorName + '\'' +
               ", description='" + description + '\'' +
               ", unit='" + unit + '\'' +
               ", userId='" + userId + '\'' +
               ", inscription='" + inscription + '\'' +
               '}';
    }
}
