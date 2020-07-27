/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.NotificationEntity;
import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.repositories.NotificationRepository;
import ca.usherbrooke.notifius.backend.translators.NotificationToEntityTranslator;
import ca.usherbrooke.notifius.backend.translators.ServiceToEntityTranslator;
import ca.usherbrooke.notifius.backend.translators.UserToEntityTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static ca.usherbrooke.notifius.backend.models.Service.TEST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest
{
    @Mock
    private Notification notificationMock;
    @Mock
    private User userMock;

    @Spy
    private NotificationRepository notificationRepositorySpy;
    @Spy
    private NotificationToEntityTranslator notificationToEntityTranslatorSpy;
    @Spy
    private UserToEntityTranslator userToEntityTranslatorSpy;
    @Spy
    private ServiceToEntityTranslator serviceToEntityTranslatorSpy;

    @InjectMocks
    private NotificationService notificationService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        when(notificationMock.getService()).thenReturn(TEST);

        notificationToEntityTranslatorSpy.setServiceToEntityTranslator(serviceToEntityTranslatorSpy);
        userToEntityTranslatorSpy.setServiceToEntityTranslator(serviceToEntityTranslatorSpy);
        userToEntityTranslatorSpy.setNotificationToEntityTranslator(notificationToEntityTranslatorSpy);
    }

    @Test
    public void testCreateOK()
    {
        notificationService.create(notificationMock, userMock);
        verify(notificationRepositorySpy).save(any(NotificationEntity.class));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateWithNotificationNull()
    {
        notificationService.create(null, userMock);
        verifyNoInteractions(notificationRepositorySpy);
    }

    @Test
    public void testCreateWithUserNull()
    {
        notificationService.create(notificationMock, null);
        verifyNoInteractions(notificationRepositorySpy);
    }

}