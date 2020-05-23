package ca.usherbrooke.notifius.repositories;

import ca.usherbrooke.notifius.entities.NotificationEntity;
import ca.usherbrooke.notifius.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>
{
    @Query("SELECT n " +
           "FROM notification n " +
           "INNER JOIN notifius_user u ON n.user = u.id "+
           "WHERE u.id = ?1 AND n.service = ?2")
    List<NotificationEntity> findAllForUserWithService(String user, ServiceEntity service);

    @Query("SELECT n " +
           "FROM notification n " +
           "INNER JOIN notifius_user u ON n.user = u.id "+
           "WHERE u.id = ?1 AND n.date >= ?2")
    List<NotificationEntity> findAllForUserWithDateSince(String user, Date date);

    @Query("SELECT n " +
           "FROM notification n " +
           "INNER JOIN notifius_user u ON n.user = u.id "+
           "WHERE u.id = ?1 AND n.service = ?2 AND n.date >= ?3")
    List<NotificationEntity> findAllForUserWithServiceAndDateSince(String userId, ServiceEntity service, Date date);

}
