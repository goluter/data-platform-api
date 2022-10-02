package com.govey.service.users.infrastructure;

import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserTimeline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTimelineRepository extends JpaRepository<UserTimeline, UUID> {
    Page<UserTimeline> findAllByUser(User user, Pageable pageable);
}
