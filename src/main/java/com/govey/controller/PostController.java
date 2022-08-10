package com.govey.controller;

import com.govey.domain.board.Post;
import com.govey.domain.user.User;
import com.govey.dto.UserDto;
import com.govey.dto.UserRequest;
import com.govey.service.PostService;
import com.govey.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor()
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @PostMapping("/")
    // TODO: 권한
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Post> write(@Valid @RequestBody Post post) {
        // FIXME: 일단 관리자 아이디로 하드코딩
        User user = userService.findByNickname("admin");
        post.setUser(user);
        post.setAuthor(user.getNickname());
        return ResponseEntity.ok(postService.write(post));
    }

    @GetMapping("/")
    // TODO: 권한
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Post>> list(@RequestParam(value="category") String category) {
        return ResponseEntity.ok(postService.findAllByCategory(category));
    }

    @GetMapping("/{id}")
    // TODO: 권한
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Post> retrieve(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.retrieve(id).get());
    }
}
