package ca.usherbrooke.notifius.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceService
{
    @PostConstruct
    public void insertServices()
    {
        for (ca.usherbrooke.notifius.models.Service service : ca.usherbrooke.notifius.models.Service.values()) {
            System.out.println(service);
        }
    }
}
