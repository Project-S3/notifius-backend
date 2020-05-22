package ca.usherbrooke.notifius.repositories;

import ca.usherbrooke.notifius.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>
{
}
