package com.govey.controller.users.surveys;

import com.govey.service.surveys.application.*;
import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyBookmark;
import com.govey.service.surveys.domain.SurveyStatus;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/v1/surveys")
public class SurveyController {
    private final SurveyService surveyService;
    private final SurveyTagService surveyTagService;
    private final SurveyBookmarkService surveyBookmarkService;
    private final SurveyRewardService surveyRewardService;
    private final PollService pollService;
    private final UserService userService;

    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Survey> add(Authentication authentication, @Valid @RequestBody SurveyRequest body) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyService.add(user, body));
    }

    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<Survey>> list(Authentication authentication,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "0") int limit,
                                             @RequestParam Optional<String> subject,
                                             @RequestParam Optional<List<SurveyStatus>> statuses
    ) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyService.page(user, page, limit, subject, statuses));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Survey> retrieve(Authentication authentication, @PathVariable UUID id) {
//        Optional<User> author = userService.getUserByUsername(authentication.getName());
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyService.retrieve(id, Optional.of(user)).get());
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Survey> update(@PathVariable UUID id, @Valid @RequestBody SurveyUpdateRequest body) {
        return ResponseEntity.ok(surveyService.update(id, body));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity delete(@PathVariable UUID id) {
        surveyService.softDelete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/polls")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Poll>> listPoll(@PathVariable UUID id) {
        return ResponseEntity.ok(pollService.findAllBySurvey(id));
    }

    @PostMapping("/{id}/polls")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Poll> addPoll(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody PollRequest body) {
        return ResponseEntity.ok(pollService.add(id, body));
    }

    @PostMapping("/{id}/bookmark")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SurveyBookmark> addBookmark(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody PollRequest body) {
        //        User reader = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyBookmarkService.add(user, id));
    }
}
