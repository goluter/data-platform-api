package com.govey.service.rewards.application;

import com.govey.controller.users.surveys.PollUserRequest;
import com.govey.service.rewards.domain.RewardUser;
import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.PollUser;
import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyStatus;
import com.govey.service.surveys.infrastructure.*;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RewardUserService {
    private final SurveyRepository surveyRepository;
    private final PollRepository pollRepository;
    private final PollItemRepository pollItemRepository;
    private final PollUserRepository pollUserRepository;
    private final PollAnswerRepository pollAnswerRepository;

    @Transactional()
    public List<PollUser> listByPollId(UUID pollId) {
        Poll poll = pollRepository.findById(pollId).get();
        return pollUserRepository.findAllByPoll(poll);
    }

    @Transactional()
    public void softDelete(UUID id) {
        Poll result = pollRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        pollRepository.save(result);
    }
}
