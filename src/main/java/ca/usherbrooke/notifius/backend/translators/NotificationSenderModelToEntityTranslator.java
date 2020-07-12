/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.translators;

import ca.usherbrooke.notifius.backend.entities.NotificationSenderEntity;
import ca.usherbrooke.notifius.backend.models.NotificationSenderModel;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderModelToEntityTranslator
{
    public NotificationSenderEntity toEntity(NotificationSenderModel notificationSenderModel)
    {
        return new NotificationSenderEntity().withId(notificationSenderModel.getId())
                                             .withDisplayName(notificationSenderModel.getDisplayName());
    }

    public NotificationSenderModel toModel(NotificationSenderEntity notificationSenderEntity)
    {
        return new NotificationSenderModel().withId(notificationSenderEntity.getId())
                                            .withDisplayName(notificationSenderEntity.getDisplayName());
    }
}
