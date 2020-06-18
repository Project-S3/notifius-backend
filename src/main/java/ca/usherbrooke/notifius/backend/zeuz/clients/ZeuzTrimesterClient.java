package ca.usherbrooke.notifius.backend.zeuz.clients;

import ca.usherbrooke.notifius.backend.zeuz.models.Trimester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.util.*;

@Service
public class ZeuzTrimesterClient
{
    private static final String TRIMESTER_URL_WITH_PARAMS = "/trimestre" +
                                                            "?inscription={inscription}" +
                                                            "&trimestre_id={trimesterId}";

    @Autowired
    private DateFormat zeuzDateFormat;

    @Value("${zeuz.ms.base-url}")
    private String zeuzMsBaseURL;

    public List<Trimester> getTrimester(Date inscription)
    {
        return getTrimester(inscription, null);
    }

    public List<Trimester> getTrimester(Date inscription, String trimesterId)
    {
        Map<String, String> param = new HashMap<>();
        param.put("inscription", zeuzDateFormat.format(inscription));
        param.put("trimesterId", trimesterId);

        RestTemplate restTemplate = new RestTemplate();
        Trimester[] result = restTemplate.getForObject(zeuzMsBaseURL + TRIMESTER_URL_WITH_PARAMS,
                                                       Trimester[].class,
                                                       param);
        return result == null ? new ArrayList<>() : Arrays.asList(result);
    }
}