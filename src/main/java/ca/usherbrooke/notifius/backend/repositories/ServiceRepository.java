package ca.usherbrooke.notifius.backend.repositories;

import ca.usherbrooke.notifius.backend.entities.ServiceEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderEntity;
import ca.usherbrooke.notifius.backend.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Service>
{
}