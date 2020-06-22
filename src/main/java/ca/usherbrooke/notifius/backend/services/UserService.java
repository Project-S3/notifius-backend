package ca.usherbrooke.notifius.backend.services;

import ca.usherbrooke.notifius.backend.entities.*;
import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.repositories.*;
import ca.usherbrooke.notifius.backend.resterrors.NotificationSenderNotFoundException;
import ca.usherbrooke.notifius.backend.translators.UserToEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ca.usherbrooke.notifius.backend.notificationsenders.EmailNotificationSender.EMAIL_SENDER_ID;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private NotificationSenderRepository notificationSenderRepository;
    @Autowired
    private UserNotificationSenderRepository userNotificationSenderRepository;
    @Autowired
    private UserToEntityTranslator userToEntityTranslator;
    @Autowired
    private UserNotificationSenderService userNotificationSenderService;

    @Value("${notifius.email.domain}")
    private String notifiusEmailDomain;

    public void createUser(String userId)
    {
        UserEntity userEntity = constructUserEntity(userId);
        userRepository.save(userEntity);

        userNotificationSenderService.createOrUpdate(userEntity,
                                                     EMAIL_SENDER_ID,
                                                     String.format("%s@%s", userId, notifiusEmailDomain));
    }

    public List<User> createAllUser(List<String> allUserId)
    {
        List<UserEntity> userEntities = new ArrayList<>();
        List<UserNotificationSenderEntity> userNotificationSenderEntities = new ArrayList<>();

        for (String id : allUserId) {
            UserEntity userEntity = constructUserEntity(id);
            userEntities.add(userEntity);

            NotificationSenderEntity notificationSenderEntity = notificationSenderRepository.findById("EMAIL_SENDER").orElseThrow(() -> new NotificationSenderNotFoundException());

            UserNotificationSenderEntity userNotificationSenderEntity = new UserNotificationSenderEntity()
                    .withNotificationSender(notificationSenderEntity)
                    .withUser(userEntity)
                    .withValue(String.format("%s@%s", id, notifiusEmailDomain))
                    .withId(new UserNotificationSenderKey(id,"EMAIL_SENDER"));
            userNotificationSenderEntities.add(userNotificationSenderEntity);

        }

        userRepository.saveAll(userEntities);
        userNotificationSenderRepository.saveAll(userNotificationSenderEntities);

        return userEntities.stream()
                           .map(userToEntityTranslator::toModel)
                           .collect(Collectors.toList());
    }

    public void updateUser(User user)
    {
        userRepository.save(userToEntityTranslator.toEntity(user));
    }

    public Optional<User> getUser(String userId)
    {
        return userRepository.findById(userId).map(userToEntityTranslator::toModel);
    }

    public List<User> getAllUser(List<String> allUserId)
    {
        return userRepository.findAllById(allUserId)
                             .stream()
                             .map(userToEntityTranslator::toModel)
                             .collect(Collectors.toList());
    }

    private UserEntity constructUserEntity(String userId)
    {
        return new UserEntity().withId(userId)
                               .withNotifications(new HashSet<>())
                               .withEnableServices(new HashSet<>(serviceRepository.findAll()));

    }
}
