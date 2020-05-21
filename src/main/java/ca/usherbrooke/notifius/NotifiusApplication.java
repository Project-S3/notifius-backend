package ca.usherbrooke.notifius;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCasClient
@SpringBootApplication
public class NotifiusApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NotifiusApplication.class, args);
    }
}
