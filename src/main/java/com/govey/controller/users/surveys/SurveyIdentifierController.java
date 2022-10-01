package com.govey.controller.users.surveys;

import com.govey.service.surveys.application.PollService;
import com.govey.service.surveys.application.PollUserService;
import com.govey.service.surveys.application.SurveyIdentifierService;
import com.govey.service.surveys.application.SurveyService;
import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.PollUser;
import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyIdentifier;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/v1/identifiers")
public class SurveyIdentifierController {
    private final UserService userService;
    private final PollService pollService;
    private final SurveyIdentifierService surveyIdentifierService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SurveyIdentifier> retrieve(@PathVariable UUID id, Authentication authentication) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(surveyIdentifierService.retrieve(id));
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SurveyIdentifier> update(@PathVariable UUID id, @Valid @RequestBody SurveyIdentifierRequest body) {
        return ResponseEntity.ok(surveyIdentifierService.update(id, body));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity delete(@PathVariable UUID id) {
        pollService.softDelete(id);
        return ResponseEntity.ok().build();
    }
}
