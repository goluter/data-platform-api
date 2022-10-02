package com.govey.controller.users.self;

import com.govey.controller.users.surveys.*;
import com.govey.service.surveys.application.*;
import com.govey.service.surveys.domain.*;
import com.govey.service.users.application.UserPointService;
import com.govey.service.users.application.UserRewardService;
import com.govey.service.users.application.UserService;
import com.govey.service.users.application.UserTimelineService;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserPoint;
import com.govey.service.users.domain.UserReward;
import com.govey.service.users.domain.UserTimeline;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/v1/self")
public class SelfController {
    private final ReportService reportService;
    private final ReportBookmarkService reportBookmarkService;
    private final SurveyService surveyService;
    private final SurveyBookmarkService surveyBookmarkService;
    private final SurveyUserService surveyUserService;
    private final UserService userService;
    private final UserPointService userPointService;
    private final UserTimelineService userTimelineService;
    private final UserRewardService userRewardService;

    @GetMapping("/info")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> info(Authentication authentication) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/reports/registrations")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<Report>> listRegistrationReports(Authentication authentication,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "0") int limit
    ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(reportService.page(user, page, limit));
    }

    @GetMapping("/reports/bookmarks")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<ReportBookmark>> listBookmarkingReports(Authentication authentication,
                                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "limit", defaultValue = "0") int limit
                                                                ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(reportBookmarkService.page(user, page, limit));
    }

    @GetMapping("/surveys/registrations")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<Survey>> listRegistrationSurveys(Authentication authentication,
                                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "limit", defaultValue = "0") int limit
                                                                ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyService.page(user, page, limit));
    }

    @GetMapping("/surveys/bookmarks")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<SurveyBookmark>> listBookmarkingSurveys(Authentication authentication,
                                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                                       @RequestParam(value = "limit", defaultValue = "0") int limit
                                                                       ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyBookmarkService.page(user, page, limit));
    }

    @GetMapping("/surveys/answers")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<SurveyUser>> listAnswerSurveys(Authentication authentication,
                                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                                       @RequestParam(value = "limit", defaultValue = "0") int limit
    ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyUserService.page(user, page, limit));
    }

    @GetMapping("/points")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<UserPoint>> listPoints(Authentication authentication,
                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "0") int limit
    ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(userPointService.page(user, page, limit));
    }

    @GetMapping("/timelines")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<UserTimeline>> listTimeline(Authentication authentication,
                                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "limit", defaultValue = "0") int limit
    ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(userTimelineService.page(user, page, limit));
    }

    @GetMapping("/rewards")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<UserReward>> listRewards(Authentication authentication,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "limit", defaultValue = "0") int limit
    ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(userRewardService.page(user, page, limit));
    }
}
