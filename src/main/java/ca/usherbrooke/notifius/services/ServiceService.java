package ca.usherbrooke.notifius.services;

import ca.usherbrooke.notifius.entities.ServiceEntity;
import ca.usherbrooke.notifius.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ServiceService
{
    @Autowired
    private ServiceRepository serviceRepository;

    @PostConstruct
    public void insertServices()
    {
        serviceRepository.saveAll(Arrays.stream(ca.usherbrooke.notifius.models.Service.values())
                                        .map(ServiceEntity::new)
                                        .collect(Collectors.toSet()));
    }
}
