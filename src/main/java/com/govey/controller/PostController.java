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
import java.util.UUID;

@Controller
@RequiredArgsConstructor()
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    // TODO: 권한
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Post>> list(HttpServletRequest request) {
        return ResponseEntity.ok(postService.list());
    }

    @GetMapping("/{id}")
    // TODO: 권한
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Post> retrieve(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.retrieve(id).get());
    }
}
