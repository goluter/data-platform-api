package com.govey.controller.users.posts;

import com.govey.service.posts.application.PostService;
import com.govey.service.posts.domain.Post;
import com.govey.service.user.application.UserService;
import com.govey.service.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/v1/posts")
public class PostController {
    private final PostService service;

    private final UserService userService;

    @GetMapping("/page")
//    @PreAuthorize("hasAnyRole('USER','ADMIN','GUEST')")
    public ResponseEntity<Page<Post>> page(Authentication authentication, @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "limit", defaultValue = "0") int limit,
                                                   @RequestParam Optional<String> subject,
                                                   @RequestParam Optional<String> category) {
//        User reader = userService.getUserByUsername(authentication.getName()).get();
        User reader = userService.getUserByUsername("admin").get();
        Page<Post> result = service.page(reader, page, limit, category, subject);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN','GUEST')")
    public ResponseEntity<Post> add(Authentication authentication, @Valid @RequestBody PostRequest body) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User author = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(service.add(author, body));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN','GUEST')")
    public ResponseEntity<Post> retrieve(Authentication authentication,@PathVariable UUID id) {
//        Optional<User> author = userService.getUserByUsername(authentication.getName());
        return ResponseEntity.ok(service.retrieve(id, userService.getUserByUsername("admin")).get());
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN','GUEST')")
    public ResponseEntity<Post> update(@PathVariable UUID id, @Valid @RequestBody PostRequest body) {
        return ResponseEntity.ok(service.update(id, body));
    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN','GUEST')")
//    public ResponseEntity delete(@PathVariable UUID id) {
//        service.softDelete(id);
//        return ResponseEntity.ok().build();
//    }
}
