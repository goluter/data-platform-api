package com.govey.controller.users.surveys;

import com.govey.service.surveys.application.PollService;
import com.govey.service.surveys.application.PollUserService;
import com.govey.service.surveys.application.SurveyService;
import com.govey.service.surveys.domain.*;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/users/v1/polls")
public class PollController {
    private final UserService userService;
    private final SurveyService surveyService;
    private final PollService pollService;
    private final PollUserService pollUserService;

    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Poll>> list(Authentication authentication) {
        return ResponseEntity.ok(pollService.list());
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PollResponse> retrieve(@PathVariable UUID id, Authentication authentication) {
        Poll poll = pollService.retrieve(id).get();
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setSurvey(poll.getSurvey());
        pollResponse.setSubject(poll.getSubject());
        pollResponse.setContent(poll.getContent());
        pollResponse.setDuplicable(poll.getDuplicable());
        pollResponse.setType(poll.getType());
        return ResponseEntity.ok(pollResponse);
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Poll> update(@PathVariable UUID id, @Valid @RequestBody Poll body) {
        return ResponseEntity.ok(pollService.update(id, body));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity delete(@PathVariable UUID id) {
        pollService.softDelete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/poll-users")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PollUser>> listAnswer(@PathVariable UUID id) {
        return ResponseEntity.ok(pollUserService.listByPollId(id));
    }

    @PostMapping("/{id}/poll-users")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PollUser> answer(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody PollUserRequest body) {
        if (authentication.getName() != null &&!authentication.getName().isEmpty()) {
            Optional<User> author = userService.getUserByUsername(authentication.getName());
            return ResponseEntity.ok(pollUserService.add(id, author, body));
        } else {
            return ResponseEntity.ok(pollUserService.add(id, Optional.empty(), body));
        }
    }
}
