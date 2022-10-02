package com.govey.service.users.infrastructure;

import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserPoint;
import com.govey.service.users.domain.UserTimeline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPointRepository extends JpaRepository<UserPoint, UUID> {
    Page<UserPoint> findAllByUser(User user, Pageable pageable);
}
