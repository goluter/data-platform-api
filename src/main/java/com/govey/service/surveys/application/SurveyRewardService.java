package com.govey.service.surveys.application;

import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyReward;
import com.govey.service.surveys.domain.SurveyStatus;
import com.govey.service.surveys.infrastructure.SurveyRewardRepository;
import com.govey.service.surveys.infrastructure.SurveyRepository;
import com.govey.service.surveys.infrastructure.SurveyRewardRepository;
import com.govey.service.surveys.infrastructure.SurveyTagRepository;
import com.govey.service.users.domain.User;
import com.govey.service.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyRewardService {
    private final SurveyRepository surveyRepository;
    private final SurveyRewardRepository surveyRewardRepository;
    private final SurveyRewardRepository SurveyRewardRepository;
    private final SurveyTagRepository surveyTagRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<SurveyReward> page(int page, int limit) {
        return SurveyRewardRepository.findAll(
                PageRequest.of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional()
    public Optional<SurveyReward> retrieve(UUID id) {
        return SurveyRewardRepository.findById(id);
    }

    @Transactional()
    public SurveyReward add(UUID surveyId, SurveyReward reward) {
        Survey survey = surveyRepository.findById(surveyId).get();
        SurveyReward entity = SurveyRewardRepository.save(SurveyReward.builder()
                .value(reward.getValue())
                .type(reward.getType())
                .survey(survey)
                .build());

        return SurveyRewardRepository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        SurveyReward result = SurveyRewardRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        SurveyRewardRepository.save(result);
    }
}
