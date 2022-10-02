package com.govey.service.store.application;

import com.govey.controller.users.storeitems.StoreItemRequest;
import com.govey.service.store.domain.StoreItem;
import com.govey.service.store.infrastructure.StoreRepository;
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
            entity.setCategory(request.getCategory());
        }
        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
        if (request.getNotice() != null) {
            entity.setNotice(request.getNotice());
        }
        if (request.getPrice() != null) {
            entity.setPrice(request.getPrice());
        }
        if (request.getImageUrl() != null) {
            entity.setImageUrl(request.getImageUrl());
        }
        if (request.getStockCount() != null) {
            entity.setStockCount(request.getStockCount());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
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
