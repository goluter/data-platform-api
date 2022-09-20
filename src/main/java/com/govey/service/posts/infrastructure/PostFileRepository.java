package com.govey.service.posts.infrastructure;

import com.govey.service.posts.domain.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PostFileRepository extends JpaRepository<PostFile, UUID> {
}
