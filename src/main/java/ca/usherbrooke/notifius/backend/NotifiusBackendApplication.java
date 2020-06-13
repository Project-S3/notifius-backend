package ca.usherbrooke.notifius.backend;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCasClient
@SpringBootApplication
public class NotifiusBackendApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NotifiusBackendApplication.class, args);
    }
}
