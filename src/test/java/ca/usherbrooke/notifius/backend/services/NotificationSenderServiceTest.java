/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.NotificationSenderEntity;
import ca.usherbrooke.notifius.backend.models.Notification;
import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.notificationsenders.NotificationSender;
import ca.usherbrooke.notifius.backend.repositories.NotificationSenderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Set;

import static ca.usherbrooke.notifius.backend.models.Service.TEST;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificationSenderServiceTest
{
    public static final String NOTIFICATION_SENDER_ID = "NOTIFICATION_SENDER";
    public static final String NOTIFICATION_SENDER_DISPLAY_NAME = "notification sender";

    public static final Service TEST_NOTIFICATION_SERVICE = TEST;

    @Mock
    private NotificationSender testNotificationSenderMock;
    @Mock
    private Notification notificationMock;
    @Mock
    private User userMock;

    @Spy
    private NotificationSenderRepository notificationSenderRepositorySpy;
    @Spy
    private NotificationSender notificationSenderSpy;

    @InjectMocks
    private NotificationSenderService notificationSenderService;

    @Captor
    private ArgumentCaptor<NotificationSenderEntity> NotificationSenderEntityCaptor;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        when(testNotificationSenderMock.getNotificationSenderDisplayName())
                .thenReturn(NOTIFICATION_SENDER_DISPLAY_NAME);
        when(testNotificationSenderMock.getNotificationSenderId()).thenReturn(NOTIFICATION_SENDER_ID);

        when(notificationMock.getService()).thenReturn(TEST_NOTIFICATION_SERVICE);
    }

    @Test
    public void testAddNotificationSenderSubscriberWithNull()
    {
        verifyNoInteractions(notificationSenderRepositorySpy);
        assertEquals(0, notificationSenderService.getNotificationSenderSubscribers().size());
    }

    @Test
    public void testAddNotificationSenderSubscriberOK()
    {
        notificationSenderService.addNotificationSenderSubscriber(testNotificationSenderMock);

        verify(notificationSenderRepositorySpy).save(NotificationSenderEntityCaptor.capture());
        NotificationSenderEntity notificationSenderEntity = NotificationSenderEntityCaptor.getValue();

        assertEquals(NOTIFICATION_SENDER_ID, notificationSenderEntity.getId());
        assertEquals(NOTIFICATION_SENDER_DISPLAY_NAME, notificationSenderEntity.getDisplayName());
        assertEquals(1, notificationSenderService.getNotificationSenderSubscribers().size());
    }

    @Test
    public void testSendNotificationsOK()
    {

        when(userMock.getEnableServices()).thenReturn(Set.of(TEST));
        notificationSenderService.setNotificationSenderSubscribers(List.of(notificationSenderSpy));
        notificationSenderService.sendNotifications(notificationMock, userMock);
        verify(notificationSenderSpy).sendNotifications(notificationMock, userMock);
    }

    @Test
    public void testSendNotificationsNotEnable()
    {
        when(userMock.getEnableServices()).thenReturn(Set.of());
        notificationSenderService.setNotificationSenderSubscribers(List.of(notificationSenderSpy));
        notificationSenderService.sendNotifications(notificationMock, userMock);
        verifyNoInteractions(notificationSenderSpy);
    }

    @Test
    public void testSendNotificationsWithNotificationNull()
    {
        notificationSenderService.setNotificationSenderSubscribers(List.of(notificationSenderSpy));
        notificationSenderService.sendNotifications(null, userMock);
        verifyNoInteractions(notificationSenderSpy);
    }

    @Test
    public void testSendNotificationsWithUserNull()
    {
        notificationSenderService.setNotificationSenderSubscribers(List.of(notificationSenderSpy));
        notificationSenderService.sendNotifications(notificationMock, null);
        verifyNoInteractions(notificationSenderSpy);
    }
}