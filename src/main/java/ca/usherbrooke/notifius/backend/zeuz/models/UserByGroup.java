package ca.usherbrooke.notifius.backend.zeuz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserByGroup
{
    @JsonProperty("app")
    private String appName;
    @JsonProperty("cip_etudiant")
    private String userId;
    @JsonProperty("departement")
    private String department;
    @JsonProperty("inscription")
    private String inscription;
    @JsonProperty("nom")
    private String userLastName;
    @JsonProperty("prenom")
    private String userFirstName;
    @JsonProperty("profil_id")
    private String profileId;
    @JsonProperty("programme")
    private String program;
    @JsonProperty("trimestre_id")
    private String trimesterId;
    @JsonProperty("unite_id")
    private String activityId;

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getInscription()
    {
        return inscription;
    }

    public void setInscription(String inscription)
    {
        this.inscription = inscription;
    }

    public String getUserLastName()
    {
        return userLastName;
    }

    public void setUserLastName(String userLastName)
    {
        this.userLastName = userLastName;
    }

    public String getUserFirstName()
    {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName)
    {
        this.userFirstName = userFirstName;
    }

    public String getProfileId()
    {
        return profileId;
    }

    public void setProfileId(String profileId)
    {
        this.profileId = profileId;
    }

    public String getProgram()
    {
        return program;
    }

    public void setProgram(String program)
    {
        this.program = program;
    }

    public String getTrimesterId()
    {
        return trimesterId;
    }

    public void setTrimesterId(String trimesterId)
    {
        this.trimesterId = trimesterId;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserByGroup that = (UserByGroup) o;

        if (!Objects.equals(appName, that.appName)) return false;
        if (!Objects.equals(userId, that.userId)) return false;
        if (!Objects.equals(department, that.department)) return false;
        if (!Objects.equals(inscription, that.inscription)) return false;
        if (!Objects.equals(userLastName, that.userLastName)) return false;
        if (!Objects.equals(userFirstName, that.userFirstName))
            return false;
        if (!Objects.equals(profileId, that.profileId)) return false;
        if (!Objects.equals(program, that.program)) return false;
        if (!Objects.equals(trimesterId, that.trimesterId)) return false;
        return Objects.equals(activityId, that.activityId);
    }

    @Override
    public int hashCode()
    {
        int result = appName != null ? appName.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (inscription != null ? inscription.hashCode() : 0);
        result = 31 * result + (userLastName != null ? userLastName.hashCode() : 0);
        result = 31 * result + (userFirstName != null ? userFirstName.hashCode() : 0);
        result = 31 * result + (profileId != null ? profileId.hashCode() : 0);
        result = 31 * result + (program != null ? program.hashCode() : 0);
        result = 31 * result + (trimesterId != null ? trimesterId.hashCode() : 0);
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "UserByGroup{" +
               "appName='" + appName + '\'' +
               ", userId='" + userId + '\'' +
               ", department='" + department + '\'' +
               ", inscription='" + inscription + '\'' +
               ", userLastName='" + userLastName + '\'' +
               ", userFirstName='" + userFirstName + '\'' +
               ", profileId='" + profileId + '\'' +
               ", program='" + program + '\'' +
               ", trimesterId='" + trimesterId + '\'' +
               ", activityId='" + activityId + '\'' +
               '}';
    }
}
