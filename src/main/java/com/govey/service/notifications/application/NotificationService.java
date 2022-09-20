package com.govey.service.notifications.application;

import com.govey.controller.admins.notifications.NotificationDto;
import com.govey.service.notifications.domain.Notification;
import com.govey.service.notifications.infrastructure.NotificationRepository;
import com.govey.service.users.domain.User;
import com.govey.service.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final UserRepository userRepository;

    @Transactional
    public List<Notification> list() {
        return repository.findAll();
    }

    @Transactional
    public List<Notification> listByUserId(User user) {
        return repository.findAllByUser(user);
    }

    @Transactional
    public List<Notification> checkAll(User user) {
        List<Notification> notifications = repository.findAllByUser(user).stream().map(notification -> {
            notification.setIsRead(true);
            return notification;
        }).collect(Collectors.toList());
        return repository.saveAll(notifications);
    }

    @Transactional(readOnly = true)
    public Optional<Notification> retrieve(UUID id) {
        return repository.findById(id);
    }

    @Transactional()
    public Notification update(UUID boardId, Notification request) {
        Notification entity = repository.findById(boardId).orElseThrow(() -> new IllegalStateException(
                boardId + " does not exists")
        );

        return repository.save(entity);
    }

    @Transactional()
    public Notification add(NotificationDto dto) {
        User user = userRepository.findById(dto.getUserId()).get();

        Notification notification = Notification
                .builder()
                .user(user)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
        notification.setId(UUID.randomUUID());

        return repository.save(notification);
    }

    @Transactional()
    public List<Notification> addAll(NotificationDto dto) {
        List<User> users = userRepository.findAll();

        List<Notification> notifications = users.stream().map(user-> {
            Notification notification = Notification
                    .builder()
                    .user(user)
                    .title(dto.getTitle())
                    .description(dto.getDescription())
                    .build();
            notification.setId(UUID.randomUUID());

            return notification;
        }).collect(Collectors.toList());

        return repository.saveAll(notifications);
    }

    @Transactional()
    public void softDelete(UUID id) {
        Notification result = repository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        repository.save(result);
    }
}
