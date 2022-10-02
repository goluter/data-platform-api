package com.govey.controller.users.users;

import com.govey.config.TokenProvider;
import com.govey.controller.users.auth.LoginDto;
import com.govey.controller.users.auth.TokenDto;
import com.govey.filter.JwtFilter;
import com.govey.service.notifications.application.NotificationService;
import com.govey.service.notifications.domain.Notification;
import com.govey.service.posts.domain.Post;
import com.govey.service.rewards.domain.Reward;
import com.govey.service.surveys.application.SurveyBookmarkService;
import com.govey.service.surveys.domain.SurveyBookmark;
import com.govey.service.users.application.UserDto;
import com.govey.service.users.application.UserRewardService;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserReward;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController("Users.UserController")
@RequiredArgsConstructor
@RequestMapping("/users/v1/users")
public class UserController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    private final NotificationService notificationService;
    private final UserRewardService userRewardService;

    @GetMapping("/")
    @ApiOperation(value = "유저 목록 조회")
    public ResponseEntity<Page<User>> page(Authentication authentication,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "0") int limit,
                                             @RequestParam Optional<String> sortKey,
                                             @RequestParam Optional<Boolean> isDesc)  {
//        User reader = userService.getUserByUsername(authentication.getName()).get();
        return ResponseEntity.ok(userService.page(page, limit, sortKey, isDesc));
    }

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

    @GetMapping("/{uuid}/rewards")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Page<UserReward>> getRewards(@PathVariable UUID uuid, @RequestParam(value = "type", defaultValue = "0") String type,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "limit", defaultValue = "0") int limit) {
        User user = userService.retrieve(uuid).get();
        return ResponseEntity.ok(userRewardService.page(user, type, page, limit));
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
