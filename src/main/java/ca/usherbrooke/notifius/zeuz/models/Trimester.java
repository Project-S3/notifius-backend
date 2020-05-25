package ca.usherbrooke.notifius.zeuz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}


