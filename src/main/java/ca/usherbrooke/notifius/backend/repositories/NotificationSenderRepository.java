/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.repositories;

import ca.usherbrooke.notifius.backend.entities.NotificationSenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSenderRepository extends JpaRepository<NotificationSenderEntity, String>
{
}