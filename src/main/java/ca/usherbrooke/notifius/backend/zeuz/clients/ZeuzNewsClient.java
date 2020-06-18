package ca.usherbrooke.notifius.backend.zeuz.clients;

import ca.usherbrooke.notifius.backend.zeuz.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.util.*;

@Service
public class ZeuzNewsClient
{
    private static final String NEWS_URL_WITH_PARAMS = "/nouvelle" +
                                                       "?inscription={inscription}" +
                                                       "&cip={userId}" +
                                                       "&id={newsId}";

    @Autowired
    private DateFormat zeuzDateFormat;

    @Value("${zeuz.ms.base-url}")
    private String zeuzMsBaseURL;

    public List<News> getNews(Date inscription, String userId, String newsId)
    {
        Map<String, String> param = new HashMap<>();
        param.put("inscription", zeuzDateFormat.format(inscription));
        param.put("userId", userId);
        param.put("newsId", newsId);

        RestTemplate restTemplate = new RestTemplate();
        News[] result = restTemplate.getForObject(zeuzMsBaseURL + NEWS_URL_WITH_PARAMS,
                                                  News[].class,
                                                  param);
        return result == null ? new ArrayList<>() : Arrays.asList(result);
    }
}
