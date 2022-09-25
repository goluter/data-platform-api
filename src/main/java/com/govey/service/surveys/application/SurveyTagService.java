package com.govey.service.surveys.application;

import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyTag;
import com.govey.service.surveys.domain.SurveyStatus;
import com.govey.service.surveys.domain.SurveyTag;
import com.govey.service.surveys.infrastructure.SurveyTagRepository;
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
public class SurveyTagService {
    private final SurveyRepository surveyRepository;
    private final SurveyRewardRepository surveyRewardRepository;
    private final SurveyTagRepository SurveyTagRepository;
    private final SurveyTagRepository surveyTagRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<SurveyTag> page(int page, int limit) {
        return SurveyTagRepository.findAll(
                PageRequest.of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional()
    public Optional<SurveyTag> retrieve(UUID id) {
        return SurveyTagRepository.findById(id);
    }

    @Transactional()
    public SurveyTag add(UUID surveyId, SurveyTag surveyTag) {
        Survey survey = surveyRepository.findById(surveyId).get();
        SurveyTag entity = SurveyTagRepository.save(SurveyTag.builder()
                .value(surveyTag.getValue())
                .survey(survey)
                .build());

        return SurveyTagRepository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        SurveyTag result = SurveyTagRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        SurveyTagRepository.save(result);
    }
}
