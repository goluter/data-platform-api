package com.govey.service.users.infrastructure;

import com.govey.service.rewards.domain.Reward;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserReward;
import com.govey.service.users.domain.UserTimeline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, UUID> {
    Optional<UserReward> findByUserAndReward(User user, Reward reward);

    Page<UserReward> findAllByUserAndType(User user, String type, Pageable pageable);

    Page<UserReward> findAllByReward(Reward reward, Pageable pageable);

    @Query(
            value = "SELECT count(u.user.id) as c, u.user.id " +
                    "FROM UserReward u " +
                    "WHERE u.type = :type AND u.category = :category " +
                    "GROUP BY u.user.id " +
                    "ORDER BY c DESC"
    )
    Page<UserReward> findAllByTypeAndCategory(@Param("type") String type, @Param("category") String category, Pageable pageable);
}
