package com.govey.controller;

import com.govey.domain.user.User;
import com.govey.dto.UserDto;
import com.govey.dto.UserRequest;
import com.govey.service.UserService;
import org.hibernate.annotations.Fetch;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<User>> list(HttpServletRequest request) {
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("/{account}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> retrieve(@PathVariable String account) {
        return ResponseEntity.ok(userService.getUserWithAuthoritiesByAccount(account).get());
    }

    @GetMapping("/self")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getSelf(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @PatchMapping("/{account}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> update(@PathVariable String account, @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(account, request));
    }
}
