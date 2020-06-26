/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.translators;

import ca.usherbrooke.notifius.backend.entities.ServiceEntity;
import ca.usherbrooke.notifius.backend.models.Service;

@org.springframework.stereotype.Service
public class ServiceToEntityTranslator
{
    public ServiceEntity toEntity(Service service)
    {
        return new ServiceEntity().withId(service)
                                  .withDescription(service.getDescription())
                                  .withDisplayName(service.getDisplayName());
    }

    public Service toModel(ServiceEntity serviceEntity)
    {
        return serviceEntity.getId()
                            .withDescription(serviceEntity.getDescription())
                            .withDisplayName(serviceEntity.getDisplayName());
    }
}
