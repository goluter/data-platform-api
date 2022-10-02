package com.govey.controller.admins.points;

import com.govey.controller.users.users.UpdateUserRequest;
import com.govey.service.surveys.domain.SurveyBookmark;
import com.govey.service.users.application.UserPointService;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController("Admins.PointController")
@RequiredArgsConstructor
@RequestMapping("/admins/v1/points")
public class PointController {
    private final UserPointService userPointService;

    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserPoint> issue(
            Authentication authentication,
            @Valid @RequestBody UserPointRequest request
    ) {
        // NOTE: 닉네임은 노출되면 안된다.
        return ResponseEntity.ok(userPointService.add(request.getTitle(), request.getContent(), request.getAmount(), request.getReceiverId()));
    }

    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<UserPoint>> list(Authentication authentication,
                                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                                       @RequestParam(value = "limit", defaultValue = "0") int limit
    ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
//        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(userPointService.page(page, limit));
    }
}
