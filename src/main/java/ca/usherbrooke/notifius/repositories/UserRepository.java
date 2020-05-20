package ca.usherbrooke.notifius.repositories;

import ca.usherbrooke.notifius.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String>
{
}
