package ca.usherbrooke.notifius.repositories;

import ca.usherbrooke.notifius.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>
{

}