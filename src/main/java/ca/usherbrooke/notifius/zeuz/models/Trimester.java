package ca.usherbrooke.notifius.zeuz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Trimester
{
    @JsonProperty("debut")
    private String startingDate;
    @JsonProperty("fin")
    private String endingDate;
    @JsonProperty("inscription")
    private String inscription;
    @JsonProperty("trimestre")
    private String trimester;
    @JsonProperty("trimestre_id")
    private String trimesterId;

    public String getStartingDate()
    {
        return startingDate;
    }

    public void setStartingDate(String startingDate)
    {
        this.startingDate = startingDate;
    }

    public String getEndingDate()
    {
        return endingDate;
    }

    public void setEndingDate(String endingDate)
    {
        this.endingDate = endingDate;
    }

    public String getInscription()
    {
        return inscription;
    }

    public void setInscription(String inscription)
    {
        this.inscription = inscription;
    }

    public String getTrimester()
    {
        return trimester;
    }

    public void setTrimester(String trimester)
    {
        this.trimester = trimester;
    }

    public String getTrimesterId()
    {
        return trimesterId;
    }

    public void setTrimesterId(String trimesterId)
    {
        this.trimesterId = trimesterId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trimester trimester1 = (Trimester) o;

        if (!Objects.equals(startingDate, trimester1.startingDate))
            return false;
        if (!Objects.equals(endingDate, trimester1.endingDate))
            return false;
        if (!Objects.equals(inscription, trimester1.inscription))
            return false;
        if (!Objects.equals(trimester, trimester1.trimester)) return false;
        return Objects.equals(trimesterId, trimester1.trimesterId);
    }

    @Override
    public int hashCode()
    {
        int result = startingDate != null ? startingDate.hashCode() : 0;
        result = 31 * result + (endingDate != null ? endingDate.hashCode() : 0);
        result = 31 * result + (inscription != null ? inscription.hashCode() : 0);
        result = 31 * result + (trimester != null ? trimester.hashCode() : 0);
        result = 31 * result + (trimesterId != null ? trimesterId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Trimester{" +
               "startingDate='" + startingDate + '\'' +
               ", endingDate='" + endingDate + '\'' +
               ", inscription='" + inscription + '\'' +
               ", trimester='" + trimester + '\'' +
               ", trimesterId='" + trimesterId + '\'' +
               '}';
    }
}