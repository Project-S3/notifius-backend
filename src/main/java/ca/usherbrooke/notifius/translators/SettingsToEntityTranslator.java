package ca.usherbrooke.notifius.translators;

import ca.usherbrooke.notifius.entities.SettingsEntity;
import ca.usherbrooke.notifius.models.Settings;
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
                                   .withEmailServiceEnable(settings.getEmailServiceEnable())
                                   .withSmsServiceEnable(settings.getSmsServiceEnable())
                                   .withHttpServiceEnable(settings.getHttpServiceEnable())
                                   .withDiscordWebhookEnable(settings.getDiscordWebhookEnable())
                                   .withTeamsWebhookEnable(settings.getTeamsWebhookEnable())
                                   .withSlackWebhookEnable(settings.getSlackWebhookEnable())
                                   .withEnableServices(settings.getEnableServices()
                                                               .stream()
                                                               .map(serviceToEntityTranslator::toEntity)
                                                               .collect(Collectors.toSet()));
    }

    public Settings toModel(SettingsEntity settingsEntity)
    {
        return new Settings().withId(settingsEntity.getId())
                             .withEmailServiceEnable(settingsEntity.getEmailServiceEnable())
                             .withSmsServiceEnable(settingsEntity.getSmsServiceEnable())
                             .withHttpServiceEnable(settingsEntity.getHttpServiceEnable())
                             .withDiscordWebhookEnable(settingsEntity.getDiscordWebhookEnable())
                             .withTeamsWebhookEnable(settingsEntity.getTeamsWebhookEnable())
                             .withSlackWebhookEnable(settingsEntity.getSlackWebhookEnable())
                             .withEnableServices(settingsEntity.getEnableServices()
                                                               .stream()
                                                               .map(serviceToEntityTranslator::toModel)
                                                               .collect(Collectors.toSet()));
    }
}
