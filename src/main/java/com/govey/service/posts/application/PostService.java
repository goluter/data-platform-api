package com.govey.service.posts.application;

import com.govey.controller.users.posts.PostRequest;
import com.govey.service.posts.domain.Post;
import com.govey.service.posts.domain.PostSpecification;
import com.govey.service.posts.infrastructure.PostRepository;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    @Transactional(readOnly = true)
    public Page<Post> page(int page, int limit, Optional<String> category, Optional<String> subject) {
        List<Specification<Post>> specifications = new ArrayList<>();

        specifications.add(PostSpecification.find(subject, category));

        Specification<Post> specification = null;
        for (Specification<Post> spec :specifications) {
            if (specification == null) {
                specification = Specification.where(spec);
            } else {
                specification.or(Specification.where(spec));
            }
        }

        return repository.findAll(
                specification,
                PageRequest
                        .of(page, limit, Sort.by("isNotice").descending().and(Sort.by("createdAt").descending()))
        );
    }

    @Transactional()
    public Optional<Post> retrieve(UUID id) {
        return repository.findById(id);
    }

    @Transactional()
    public Post update(UUID id, PostRequest request) {
        Post post = repository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );

        if (!post.getUser().isActivated()) {
            throw new IllegalStateException("deactivated user");
        }

        if (request.getSubject() != null) {
            post.setSubject(request.getSubject());
        }
        if (request.getImageUrl() != null) {
            post.setImageUrl(request.getImageUrl());
        }
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setIsNotice(request.getIsNotice());

        return repository.save(post);
    }

    @Transactional()
    public Post add(User author, PostRequest request) {
        if (!author.isActivated()) {
            throw new IllegalStateException("deactivated user");
        }

        Post post = new Post();
        post.setId(UUID.randomUUID());
        post.setCreatedAt(LocalDateTime.now());

        post.setUser(author);
        post.setAuthor(author.getNickname());
        post.setImageUrl(request.getImageUrl());
        post.setSubject(request.getSubject());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setIsNotice(request.getIsNotice());

        return repository.save(post);
    }

    @Transactional()
    public void softDelete(UUID id) {
        Post result = repository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        repository.save(result);
    }
}
