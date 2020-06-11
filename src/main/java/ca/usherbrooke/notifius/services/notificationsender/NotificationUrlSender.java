package ca.usherbrooke.notifius.services.notificationsender;

import ca.usherbrooke.notifius.models.Notification;
import ca.usherbrooke.notifius.models.User;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class NotificationHttpSender extends NotificationSender
{
    @Override
    void sendNotifications(Notification notification, User user)
    {
        if (user.getSettings().getHttpServiceEnable()) {
            JSONObject notif = new JSONObject();
            try {
                notif.put("title", notification.getTitle());
                notif.put("content", notification.getContent());
                notif.put("date", notification.getDate());
                notif.put("service", notification.getService());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(notif.toString(), mediaType);
            Request request = new Request.Builder()
                    .url(user.getCustomUrl())
                    .method("POST", body)
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
}
