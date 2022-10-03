package com.govey.service.users.application;

import com.govey.service.surveys.domain.SurveyBookmark;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserPoint;
import com.govey.service.users.infrastructure.UserRepository;
import com.govey.service.users.infrastructure.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPointService {
    private final UserRepository userRepository;
    private final UserPointRepository userPointRepository;

    @Transactional(readOnly = true)
    public Page<UserPoint> page(int page, int limit) {
        return userPointRepository.findAll(
                PageRequest.of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional(readOnly = true)
    public Page<UserPoint> page(User user, int page, int limit) {
        return userPointRepository.findAllByUser(
                user,
                PageRequest
                        .of(page, limit, Sort.by("createdAt"))
        );
    }

    @Transactional
    public UserPoint add(String title, String content, Integer amount, UUID receiverId) {
        User issuer = userRepository.findByUsername("admin").get();
        User user = userRepository.findById(receiverId).get();
        user.setPoint(user.getPoint() + amount);
        userRepository.save(user);

        return userPointRepository.save(UserPoint.builder()
                .user(user)
                .title(title)
                .amount(amount)
                .content(content)
                .issuer(issuer)
                .build());
    }

    @Transactional()
    public Optional<UserPoint> retrieve(UUID id) {
        return userPointRepository.findById(id);
    }
}
