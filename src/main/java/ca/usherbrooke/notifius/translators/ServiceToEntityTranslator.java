package ca.usherbrooke.notifius.translators;

import ca.usherbrooke.notifius.entities.ServiceEntity;
import ca.usherbrooke.notifius.models.Service;

@org.springframework.stereotype.Service
public class ServiceToEntityTranslator
{
    public ServiceEntity toEntity(Service service)
    {
        return new ServiceEntity().withId(service);
    }

    public Service toModel(ServiceEntity serviceEntity)
    {
        return serviceEntity.getId();
    }
}
