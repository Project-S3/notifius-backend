package ca.usherbrooke.notifius.services;

import ca.usherbrooke.notifius.entities.ServiceEntity;
import ca.usherbrooke.notifius.models.Service;
import ca.usherbrooke.notifius.repositories.ServiceRepository;
import ca.usherbrooke.notifius.resterrors.UnknownServiceException;
import ca.usherbrooke.notifius.translators.ServiceToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;
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

    @PostConstruct
    public void insertServices()
    {
        serviceRepository.saveAll(Arrays.stream(Service.values())
                                        .map(ServiceEntity::new)
                                        .collect(Collectors.toSet()));
    }
}
