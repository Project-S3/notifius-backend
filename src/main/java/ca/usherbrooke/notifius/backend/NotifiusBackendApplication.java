/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@EnableAsync
@SpringBootApplication
public class NotifiusBackendApplication
{
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("Spring boot application running in UTC timezone :" + new Date());
    }

    public static void main(String[] args)
    {
        SpringApplication.run(NotifiusBackendApplication.class, args);
    }
}
