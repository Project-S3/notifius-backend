package ca.usherbrooke.notifius.backend.repositories;

import ca.usherbrooke.notifius.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>
{
}
