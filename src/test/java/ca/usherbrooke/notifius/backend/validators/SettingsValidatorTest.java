/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.validators;

import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.models.Settings;
import ca.usherbrooke.notifius.backend.resterrors.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
public class SettingsValidatorTest
{

    private SettingsValidator settingsValidator;
    private Settings settings;

    @Before
    public void init()
    {
        settingsValidator = new SettingsValidator();
        Set<Service> enableServices = new HashSet<>();
        enableServices.add(Service.TEST);
        Map<String, String> notificationSenders = new HashMap<>();
        notificationSenders.put("EMAIL","test@usherbrooke.com");
        settings = new Settings().withEnableServices(enableServices)
                                 .withNotificationSenders(notificationSenders);
    }

    @Test(expected = SettingsContainUnknownNotificationSender.class)
    public void testContainUnknownNotificationSender()
    {
        Map<String, String> notificationSenders = new HashMap<>();
        notificationSenders.put("TEST","value");
        settingsValidator.validSettingsThrowIfNotValid(settings.withNotificationSenders(notificationSenders));
    }
}