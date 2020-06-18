package ca.usherbrooke.notifius.backend.translators;

import ca.usherbrooke.notifius.backend.entities.SettingsEntity;
import ca.usherbrooke.notifius.backend.models.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SettingsToEntityTranslator
{
    @Autowired
    private ServiceToEntityTranslator serviceToEntityTranslator;

    public SettingsEntity toEntity(Settings settings)
    {
        return new SettingsEntity().withId(settings.getId())
                                   .withEnableServices(settings.getEnableServices()
                                                               .stream()
                                                               .map(serviceToEntityTranslator::toEntity)
                                                               .collect(Collectors.toSet()));
    }

    public Settings toModel(SettingsEntity settingsEntity)
    {
        return new Settings().withId(settingsEntity.getId())
                             .withEnableServices(settingsEntity.getEnableServices()
                                                               .stream()
                                                               .map(serviceToEntityTranslator::toModel)
                                                               .collect(Collectors.toSet()));
    }
}
