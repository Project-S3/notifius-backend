package ca.usherbrooke.notifius.zeuz.clients;

import ca.usherbrooke.notifius.zeuz.models.Trimester;
import ca.usherbrooke.notifius.zeuz.models.UserByGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.util.*;

@Service
public class ZeuzUsersByGroupClient
{
    private static final String USERSBYGROUP_URL_WITH_PARAMS = "/etudiant_groupe" +
                                                            "?inscription={inscription}" +
                                                            "&trimestre_id={trimesterId}";

    @Autowired
    private DateFormat zeuzDateFormat;

    @Value("${zeuz.ms.base-url}")
    private String zeuzMsBaseURL;

    public List<UserByGroup> getUsers(Date inscription)
    {
        return getUsers(inscription, null);
    }

    public List<UserByGroup> getUsers(Date inscription, String trimesterId)
    {
        Map<String, String> param = new HashMap<>();
        param.put("inscription", zeuzDateFormat.format(inscription));
        param.put("trimesterId", trimesterId);

        RestTemplate restTemplate = new RestTemplate();
        UserByGroup[] result = restTemplate.getForObject(zeuzMsBaseURL + USERSBYGROUP_URL_WITH_PARAMS,
                                                         UserByGroup[].class,
                                                       param);
        return result == null ? new ArrayList<>() : Arrays.asList(result);
    }
}
