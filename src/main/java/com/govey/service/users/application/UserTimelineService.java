package com.govey.service.users.application;

import com.govey.controller.users.users.ChangePasswordRequest;
import com.govey.controller.users.users.UpdateUserRequest;
import com.govey.service.store.domain.StoreItem;
import com.govey.service.users.domain.Authority;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserTimeline;
import com.govey.service.users.domain.UserType;
import com.govey.service.users.infrastructure.UserRepository;
import com.govey.service.users.infrastructure.UserTimelineRepository;
import com.govey.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserTimelineService {
    private final UserRepository userRepository;
    private final UserTimelineRepository userTimelineRepository;

    @Transactional
    public UserTimeline add(UserTimeline request) {
        return userTimelineRepository.save(request);
    }

    @Transactional(readOnly = true)
    public Page<UserTimeline> page(User user, int page, int limit) {
        return userTimelineRepository.findAllByUser(
                user,
                PageRequest
                        .of(page, limit, Sort.by("createdAt"))
        );
    }
}
