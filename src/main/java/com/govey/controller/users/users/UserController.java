package com.govey.controller.users.users;

import com.govey.service.notification.application.NotificationService;
import com.govey.service.notification.domain.Notification;
import com.govey.service.user.application.UserDto;
import com.govey.service.user.application.UserService;
import com.govey.service.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController("Users.UserController")
@RequiredArgsConstructor
@RequestMapping("/users/v1/users")
public class UserController {
    private final UserService userService;
    private final NotificationService notificationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        User user = userService.add(userDto);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/self")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<User> self(Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName()).get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{uuid}/notifications")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable UUID uuid) {
        return ResponseEntity.ok(notificationService.listByUserId(userService.retrieve(uuid).get()));
    }


    @GetMapping("/{uuid}/notifications/checkAll")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<Notification>> checkAll(@PathVariable UUID uuid) {
        return ResponseEntity.ok(notificationService.checkAll(userService.retrieve(uuid).get()));
    }
}
