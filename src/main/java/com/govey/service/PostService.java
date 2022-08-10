package com.govey.service;

import com.govey.domain.board.Post;
import com.govey.domain.board.PostRepository;
import com.govey.domain.user.Authority;
import com.govey.domain.user.User;
import com.govey.domain.user.UserRepository;
import com.govey.dto.UserDto;
import com.govey.dto.UserRequest;
import com.govey.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // TODO: PostDto 받도록 수정
    @Transactional
    public Post add(Post post) {
//        Post post = Post.builder().build();
        return postRepository.save(post);
    }

    @Transactional()
    public Optional<Post> retrieve(UUID id) {
        return postRepository.findById(id);
    }

    @Transactional()
    public List<Post> list() {
        return postRepository.findAll();
    }
}
