package com.govey.controller.users.reports;

import com.govey.controller.users.surveys.PollRequest;
import com.govey.controller.users.surveys.ReportRequest;
import com.govey.service.surveys.application.ReportBookmarkService;
import com.govey.service.surveys.application.ReportService;
import com.govey.service.surveys.domain.Report;
import com.govey.service.surveys.domain.ReportBookmark;
import com.govey.service.surveys.domain.SurveyBookmark;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/v1/reports")
public class ReportsController {
    private final ReportService service;
    private final UserService userService;
    private final ReportBookmarkService reportBookmarkService;

    @GetMapping("/page")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<Report>> page(Authentication authentication, @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "0") int limit) {
        Page<Report> result = service.page(page, limit);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Report> retrieve(Authentication authentication,@PathVariable UUID id) {
        return ResponseEntity.ok(service.retrieve(id).get());
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Report> update(@PathVariable UUID id, @Valid @RequestBody ReportRequest body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @PostMapping("/{id}/bookmark")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ReportBookmark> addBookmark(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody PollRequest body) {
        User reader = userService.getUserByUsername(authentication.getName()).get();
        return ResponseEntity.ok(reportBookmarkService.add(reader, id));
    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity delete(@PathVariable UUID id) {
//        service.softDelete(id);
//        return ResponseEntity.ok().build();
//    }
}
