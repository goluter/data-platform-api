package com.govey.service.notifications.infrastructure;

import com.govey.service.notifications.domain.Notification;
import com.govey.service.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findAllByUser(User user);
}
