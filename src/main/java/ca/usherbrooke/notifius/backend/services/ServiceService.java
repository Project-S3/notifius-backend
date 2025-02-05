package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.NotificationSenderEntity;
import ca.usherbrooke.notifius.backend.entities.ServiceEntity;
import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.repositories.ServiceRepository;
import ca.usherbrooke.notifius.backend.resterrors.UnknownServiceException;
import ca.usherbrooke.notifius.backend.translators.ServiceToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceService
{
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceToEntityTranslator serviceToEntityTranslator;

    public Service getById(Service id)
    {
        return serviceRepository.findById(id)
                                .map(serviceToEntityTranslator::toModel)
                                .orElseThrow(UnknownServiceException::new);
    }

    public Map<Service, String[]> getAll()
    {
        return serviceRepository.findAll().stream().collect(
                Collectors.toMap(ServiceEntity::getId, ServiceEntity::getValues));
    }

    @PostConstruct
    public void insertServices()
    {
        serviceRepository.saveAll(Arrays.stream(Service.values())
                                        .map(ServiceEntity::new)
                                        .collect(Collectors.toSet()));
    }
}
