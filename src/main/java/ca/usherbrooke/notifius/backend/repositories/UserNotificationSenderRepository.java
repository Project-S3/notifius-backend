package ca.usherbrooke.notifius.backend.repositories;

import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationSenderRepository extends JpaRepository<UserNotificationSenderEntity, UserNotificationSenderKey>
{
}