package ca.usherbrooke.notifius.repositories;

import ca.usherbrooke.notifius.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, String>
{

}