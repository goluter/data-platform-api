package com.govey.service.users.application;

import com.govey.service.rewards.application.RewardService;
import com.govey.service.rewards.domain.Reward;
import com.govey.service.surveys.application.ReportService;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserReward;
import com.govey.service.users.domain.UserTimeline;
import com.govey.service.users.domain.UserTimelineType;
import com.govey.service.users.infrastructure.UserRepository;
import com.govey.service.users.infrastructure.UserRewardRepository;
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
public class UserRewardService {
    private final UserRewardRepository userRewardRepository;
    private final UserTimelineService userTimelineService;
    private final RewardService rewardService;

    @Transactional
    public UserReward add(User user, UUID rewardId) {
        Reward reward = rewardService.retrieve(rewardId).get();
        if (userRewardRepository.findByUserAndReward(user, reward).isPresent()) {
            throw new RuntimeException("이미 획득하셨습니다.");
        }

        // NOTE: 타임라인 등록
        userTimelineService.add(UserTimeline.builder()
                .user(user)
                .title(String.format("보상 획득"))
                .content(String.format("\"%s\"보상을 획득하셨습니다!", reward.getName()))
                .type(UserTimelineType.reward)
                .imageUrl("")
                .link("")
                .build());

        return userRewardRepository.save(UserReward.builder()
                .user(user)
                .reward(rewardService.retrieve(rewardId).get())
                .type(reward.getType())
                .build());
    }

    @Transactional(readOnly = true)
    public Page<UserReward> page(UUID rewardId,  int page, int limit, Optional<Boolean> isDesc) {
        Reward reward = rewardService.retrieve(rewardId).get();
        Sort.Direction direction = isDesc.isEmpty() ? Sort.Direction.DESC : isDesc.get() ? Sort.Direction.DESC : Sort.Direction.ASC;

        return userRewardRepository.findAllByReward(
                reward,
                PageRequest
                        .of(page, limit, Sort.by(direction, "createdAt"))
        );
    }

    @Transactional(readOnly = true)
    public Page<UserReward> page(User user, String type, int page, int limit) {
        return userRewardRepository.findAllByUserAndType(
                user,
                type,
                PageRequest
                        .of(page, limit, Sort.by("createdAt"))
        );
    }
}
