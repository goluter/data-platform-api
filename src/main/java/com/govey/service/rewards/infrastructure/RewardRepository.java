package com.govey.service.rewards.infrastructure;

import com.govey.service.rewards.domain.Reward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RewardRepository extends JpaRepository<Reward, UUID> {
    Page<Reward> findAllByTypeAndCategory(Pageable pageable, String type, String category);
}
