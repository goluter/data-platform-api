package com.govey.controller.admins.notifications;

import com.govey.service.notification.application.NotificationService;
import com.govey.service.notification.domain.Notification;
import com.govey.service.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/v1/notifications")
public class NotificationController {
    private final UserService userService;
    private final NotificationService notificationService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Notification> add(
            @Valid @RequestBody NotificationDto notificationDto
    ) {
        return ResponseEntity.ok(notificationService.add(notificationDto));
    }

    @PostMapping("/addAll")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Notification>> addAll(
            @Valid @RequestBody NotificationDto notificationDto
    ) {
        return ResponseEntity.ok(notificationService.addAll(notificationDto));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Notification>> list() {
        return ResponseEntity.ok(notificationService.list());
    }
}
