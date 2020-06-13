package ca.usherbrooke.notifius.backend.zeuz.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class ZeuzConfigurations
{
    @Bean
    public DateFormat zeuzDateFormat()
    {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
