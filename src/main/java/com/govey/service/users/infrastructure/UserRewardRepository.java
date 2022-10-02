package com.govey.service.users.infrastructure;

import com.govey.service.rewards.domain.Reward;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserReward;
import com.govey.service.users.domain.UserTimeline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, UUID> {
    Optional<UserReward> findByUserAndReward(User user, Reward reward);

    Page<UserReward> findAllByUserAndType(User user, String type, Pageable pageable);

    Page<UserReward> findAllByReward(Reward reward, Pageable pageable);
}
