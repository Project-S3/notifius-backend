package ca.usherbrooke.notifius.backend.repositories;

import ca.usherbrooke.notifius.backend.entities.NotificationEntity;
import ca.usherbrooke.notifius.backend.entities.NotificationSenderEntity;
import ca.usherbrooke.notifius.backend.entities.ServiceEntity;
import ca.usherbrooke.notifius.backend.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSenderRepository extends JpaRepository<NotificationSenderEntity, String>
{

}