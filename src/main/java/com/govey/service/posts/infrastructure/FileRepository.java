package com.govey.service.posts.infrastructure;

import com.govey.service.posts.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
}