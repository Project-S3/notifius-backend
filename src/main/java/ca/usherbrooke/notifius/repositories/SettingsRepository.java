package ca.usherbrooke.notifius.repositories;

import ca.usherbrooke.notifius.entities.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<SettingEntity, Long>
{
}
