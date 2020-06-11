package ca.usherbrooke.notifius.services;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpService
{
    public void postJson(String url, JSONObject body) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(body.toString(), mediaType);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response)
            {
            }
        });
    }
}
