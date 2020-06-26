package ca.usherbrooke.notifius.backend.repositories;

import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderEntity;
import ca.usherbrooke.notifius.backend.entities.UserNotificationSenderKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationSenderRepository extends JpaRepository<UserNotificationSenderEntity, UserNotificationSenderKey>
{
    @Query("SELECT uns " +
           "FROM user_notification_sender uns " +
           "WHERE uns.user.id = ?1")
    List<UserNotificationSenderEntity> getUserNotificationSender(String userId);
}