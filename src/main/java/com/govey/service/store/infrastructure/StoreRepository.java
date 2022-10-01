package com.govey.service.store.infrastructure;

import com.govey.service.store.domain.StoreItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreItem, UUID> {
    Page<StoreItem> findAll(Pageable pageable);

    List<StoreItem> findAllByCategory(String category);
}
