package com.govey.controller.users.surveys;

import com.govey.controller.users.posts.PostRequest;
import com.govey.service.posts.domain.Post;
import com.govey.service.surveys.application.*;
import com.govey.service.surveys.domain.*;
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
import java.util.HashMap;
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
    private final ReportService reportService;
    private final PollService pollService;
    private final SurveyStatisticsService surveyStatisticsService;
    private final PollUserService pollUserService;
    private final UserService userService;
    private final SurveyIdentifierService surveyIdentifierService;
    private final SurveyUserService surveyUserService;

    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Survey> add(Authentication authentication, @Valid @RequestBody SurveyRequest body) {
        User user = userService.getUserByUsername(authentication.getName()).get();
//        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyService.add(user, body));
    }

    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<Survey>> list(Authentication authentication,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "0") int limit,
                                             @RequestParam Optional<String> searchKey,
                                             @RequestParam Optional<String> searchValue,
                                             @RequestParam Optional<String> sortKey,
                                             @RequestParam Optional<Boolean> isDesc,
                                             @RequestParam Optional<List<SurveyStatus>> statuses
    ) {
        return ResponseEntity.ok(surveyService.page(page, limit, searchKey, searchValue, sortKey, isDesc, statuses));
    }

    @GetMapping("/curations")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Survey>> curation(Authentication authentication,
                                             @RequestParam SurveyCurationType type
    ) {
        return ResponseEntity.ok(surveyService.listCuration(type));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Survey> retrieve(Authentication authentication, @PathVariable UUID id) {
        return ResponseEntity.ok(surveyService.retrieve(id).get());
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

    @GetMapping("/{id}/statistics")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<SurveyStatisticResponse>> getStatistics(@PathVariable UUID id) {
        return ResponseEntity.ok(surveyStatisticsService.getStatistics(id));
    }

    @PostMapping("/{id}/polls")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Poll> addPoll(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody PollRequest body) {
        return ResponseEntity.ok(pollService.add(id, body));
    }

    @GetMapping("/{id}/identifiers")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<SurveyIdentifier>> listIdentifier(@PathVariable UUID id) {
        return ResponseEntity.ok(surveyIdentifierService.findAllBySurvey(id));
    }

    @PostMapping("/{id}/identifiers")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SurveyIdentifier> addIdentifier(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody SurveyIdentifierRequest body) {
        return ResponseEntity.ok(surveyIdentifierService.add(id, body));
    }

    @PostMapping("/{id}/bookmark")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SurveyBookmark> addBookmark(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody PollRequest body) {
        User reader = userService.getUserByUsername(authentication.getName()).get();
//        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyBookmarkService.add(reader, id));
    }

    @PostMapping("/{id}/rewards")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SurveyReward> addReward(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody SurveyReward body) {
        return ResponseEntity.ok(surveyRewardService.add(id, body));
    }

    @GetMapping("/{id}/rewards")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<SurveyReward>> listRewards(@PathVariable UUID id) {
        return ResponseEntity.ok(surveyRewardService.page(id, 0, 1000).getContent());
    }

    @PostMapping("/{id}/reports")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Report> addReport(Authentication authentication, @PathVariable UUID id, @Valid @RequestBody ReportRequest body) {
        User author = userService.getUserByUsername(authentication.getName()).get();
//        User author = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(reportService.add(author, id, body));
    }

    @GetMapping("/{id}/reports")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Report>> listReports(@PathVariable UUID id) {
        return ResponseEntity.ok(reportService.page(id, 0, 1000).getContent());
    }

    @PostMapping("/{id}/users")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SurveyUser> confirmSurvey(@PathVariable UUID id,
                                                    Authentication authentication) {
        User author = userService.getUserByUsername(authentication.getName()).get();
        return ResponseEntity.ok(surveyUserService.add(author, id));
    }
}
