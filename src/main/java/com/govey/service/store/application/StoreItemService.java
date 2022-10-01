package com.govey.service.store.application;

import com.govey.controller.users.posts.PostRequest;
import com.govey.controller.users.storeitems.StoreItemRequest;
import com.govey.service.store.domain.StoreItem;
import com.govey.service.store.domain.PostSpecification;
import com.govey.service.store.infrastructure.StoreRepository;
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
public class StoreItemService {
    private final StoreRepository repository;

    @Transactional(readOnly = true)
    public Page<StoreItem> page(User reader, int page, int limit) {
        return repository.findAll(
                PageRequest
                        .of(page, limit, Sort.by("createdAt"))
        );
    }

    @Transactional()
    public Optional<StoreItem> retrieve(UUID id, Optional<User> reader) {
        return repository.findById(id);
    }

    @Transactional()
    public StoreItem update(UUID id, StoreItemRequest request) {
        StoreItem entity = repository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );

        if (request.getCategory() != null) {
            request.setCategory(request.getCategory());
        }
        if (request.getName() != null) {
            request.setName(request.getName());
        }
        if (request.getDescription() != null) {
            request.setDescription(request.getDescription());
        }
        if (request.getNotice() != null) {
            request.setNotice(request.getNotice());
        }
        if (request.getPrice() != null) {
            request.setPrice(request.getPrice());
        }
        if (request.getImageUrl() != null) {
            request.setImageUrl(request.getImageUrl());
        }
        if (request.getStockCount() != null) {
            request.setStockCount(request.getStockCount());
        }
        if (request.getStatus() != null) {
            request.setStatus(request.getStatus());
        }

        return repository.save(entity);
    }

    @Transactional()
    public StoreItem add(User author, StoreItemRequest request) {
        if (!author.isActivated()) {
            throw new IllegalStateException("deactivated user");
        }

        StoreItem entity = new StoreItem();
        entity.setCategory(request.getCategory());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setNotice(request.getNotice());
        entity.setPrice(request.getPrice());
        entity.setImageUrl(request.getImageUrl());
        entity.setStockCount(request.getStockCount());
        entity.setStatus(request.getStatus());

        return repository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        StoreItem result = repository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        repository.save(result);
    }
}
