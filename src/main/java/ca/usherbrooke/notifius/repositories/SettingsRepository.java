package ca.usherbrooke.notifius.repositories;

import ca.usherbrooke.notifius.entities.SettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<SettingsEntity, Long>
{
}
