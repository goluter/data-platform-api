package com.govey.controller.users.reports;

import com.govey.controller.users.surveys.ReportRequest;
import com.govey.service.surveys.application.ReportService;
import com.govey.service.surveys.domain.Report;
import com.govey.service.users.application.UserService;
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

    @GetMapping("/page")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<Report>> page(Authentication authentication, @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "0") int limit) {
//        User reader = userService.getUserByUsername(authentication.getName()).get();
        Page<Report> result = service.page(page, limit);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Report> retrieve(Authentication authentication,@PathVariable UUID id) {
//        Optional<User> author = userService.getUserByUsername(authentication.getName());
        return ResponseEntity.ok(service.retrieve(id).get());
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Report> update(@PathVariable UUID id, @Valid @RequestBody ReportRequest body) {
        return ResponseEntity.ok(service.update(id, body));
    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity delete(@PathVariable UUID id) {
//        service.softDelete(id);
//        return ResponseEntity.ok().build();
//    }
}
