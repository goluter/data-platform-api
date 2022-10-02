package com.govey.service.rewards.infrastructure;

import com.govey.service.rewards.domain.RewardUser;
import com.govey.service.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface RewardUserRepository extends JpaRepository<RewardUser, UUID> {
    List<RewardUser> findAllByUser(User user);
}
