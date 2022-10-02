package com.govey.controller.users.users;

import com.govey.config.TokenProvider;
import com.govey.controller.users.auth.LoginDto;
import com.govey.controller.users.auth.TokenDto;
import com.govey.filter.JwtFilter;
import com.govey.service.notifications.application.NotificationService;
import com.govey.service.notifications.domain.Notification;
import com.govey.service.posts.domain.Post;
import com.govey.service.surveys.application.SurveyBookmarkService;
import com.govey.service.surveys.domain.SurveyBookmark;
import com.govey.service.users.application.UserDto;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController("Users.UserController")
@RequiredArgsConstructor
@RequestMapping("/users/v1/users")
public class UserController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    private final NotificationService notificationService;
    private final SurveyBookmarkService surveyBookmarkService;

    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signin(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

//        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

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
