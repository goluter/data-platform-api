package com.govey.controller.admins.users;

import com.govey.controller.users.users.UpdateUserRequest;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController("Admins.UserController")
@RequiredArgsConstructor
@RequestMapping("/admins/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<User>> list(HttpServletRequest request,
                                           @RequestParam(value="page", defaultValue="0") int page,
                                           @RequestParam(value="limit", defaultValue="0") int limit
    ) {
        // NOTE: 닉네임은 노출되면 안된다.
        return ResponseEntity.ok(userService.list(page, limit).map(user -> {
            user.setNickname("");
            return user;
        }));
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> retrieve(@PathVariable UUID uuid) {
        User user = userService.retrieve(uuid).get();
        // NOTE: 닉네임은 노출되면 안된다.
        user.setNickname("");
        return ResponseEntity.ok(user);
    }


    @PatchMapping("/{uuid}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> update(Authentication authentication, @PathVariable UUID uuid, @Valid @RequestBody UpdateUserRequest request) throws Exception {
        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.update(uuid, request);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/{uuid}/activate")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> activate(@PathVariable UUID uuid) throws Exception {
        return ResponseEntity.ok(userService.activate(uuid));
    }

    @PostMapping("/{uuid}/deactivate")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> deactivate(@PathVariable UUID uuid) throws Exception {
        return ResponseEntity.ok(userService.deactivate(uuid));
    }

    @PostMapping("/{uuid}/withdrawal")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> withdrawal(@PathVariable UUID uuid) throws Exception {
        return ResponseEntity.ok(userService.withdrawal(uuid));
    }
}
