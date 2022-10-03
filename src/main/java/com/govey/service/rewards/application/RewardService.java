package com.govey.service.rewards.application;

import com.govey.controller.users.rewards.RewardRequest;
import com.govey.service.rewards.domain.Reward;
import com.govey.service.rewards.infrastructure.RewardRepository;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RewardService {
    private final RewardRepository repository;

    @Transactional(readOnly = true)
    public Page<Reward> page(String type, String category, int page, int limit) {
        return repository.findAllByTypeAndCategory(
                PageRequest
                        .of(page, limit, Sort.by("createdAt")),
                type,
                category
        );
    }

    @Transactional()
    public Optional<Reward> retrieve(UUID id) {
        return repository.findById(id);
    }

    @Transactional()
    public Reward update(UUID id, RewardRequest request) {
        Reward entity = repository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );

        if (request.getType() != null) {
            entity.setType(request.getType());
        }
        if (request.getCategory() != null) {
            entity.setCategory(request.getCategory());
        }
        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getContent() != null) {
            entity.setContent(request.getContent());
        }
        if (request.getRequirements() != null) {
            entity.setRequirements(request.getRequirements());
        }
        if (request.getValue() != null) {
            entity.setValue(request.getValue());
        }
        if (request.getCount() != null) {
            entity.setCount(request.getCount());
        }

        return repository.save(entity);
    }

    @Transactional()
    public Reward add(RewardRequest request) {
        Reward entity = new Reward();
        entity.setType(request.getType());
        entity.setCategory(request.getCategory());
        entity.setName(request.getName());
        entity.setContent(request.getContent());
        entity.setRequirements(request.getRequirements());
        entity.setImageUrl(request.getImageUrl());
        entity.setValue(request.getValue());
        entity.setImageUrl(request.getImageUrl());
        entity.setCount(request.getCount());
        return repository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        Reward result = repository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        repository.save(result);
    }
}
