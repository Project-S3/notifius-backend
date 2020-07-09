/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.ServiceEntity;
import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.repositories.ServiceRepository;
import ca.usherbrooke.notifius.backend.resterrors.ServiceNotFoundException;
import ca.usherbrooke.notifius.backend.resterrors.UnknownServiceException;
import ca.usherbrooke.notifius.backend.translators.ServiceToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static ca.usherbrooke.notifius.backend.models.Service.NOTIFIUS;
import static ca.usherbrooke.notifius.backend.models.Service.TEST;

@org.springframework.stereotype.Service
public class ServiceService
{
    public static final Set<Service> EXCLUDED_SERVICES = Set.of(NOTIFIUS, TEST);

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceToEntityTranslator serviceToEntityTranslator;

    public Service getServiceByName(String service)
    {
        try {
            return Service.valueOf(service);
        } catch (IllegalArgumentException e) {
            throw new ServiceNotFoundException(service);
        }
    }

    public Service getById(Service id)
    {
        return serviceRepository.findById(id)
                                .map(serviceToEntityTranslator::toModel)
                                .orElseThrow(UnknownServiceException::new);
    }

    public Set<Service> getAll()
    {
        return serviceRepository.findAll().stream()
                                .map(serviceToEntityTranslator::toModel)
                                .collect(Collectors.toSet());
    }

    @PostConstruct
    public void insertServices()
    {
        serviceRepository.saveAll(Arrays.stream(Service.values())
                                        .map(ServiceEntity::new)
                                        .collect(Collectors.toSet()));
    }
}
