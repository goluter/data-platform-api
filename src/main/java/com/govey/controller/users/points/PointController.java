package com.govey.controller.users.points;

import com.govey.service.users.application.UserPointService;
import com.govey.service.users.domain.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/v1/points")
public class PointController {
    private final UserPointService userPointService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserPoint> get(Authentication authentication, @PathVariable UUID id) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
//        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(userPointService.retrieve(id).get());
    }
}
