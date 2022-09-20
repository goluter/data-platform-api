package com.govey.controller.users.surveys;

import com.govey.service.surveys.application.PollService;
import com.govey.service.surveys.domain.PollAnswer;
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
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/poll-items")
public class PollItemController {
    private final PollService pollService;
    private final UserService userService;

    @PostMapping("/{id}/answers")
    @PreAuthorize("hasAnyRole('USER','ADMIN','GUEST')")
    public ResponseEntity<PollAnswer> answer(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody PollItemRequest body) {
        User author = userService.getUserByUsername(authentication.getName()).get();

        return ResponseEntity.ok(pollService.answer(author, id));
    }

    @GetMapping("/{id}/answers")
    @PreAuthorize("hasAnyRole('USER','ADMIN','GUEST')")
    public ResponseEntity<List<PollAnswer>> listAnswer(@PathVariable UUID id) {
        return ResponseEntity.ok(pollService.listAnswerByItem(id));
    }
}
