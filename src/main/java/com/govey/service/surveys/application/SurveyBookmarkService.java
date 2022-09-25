package com.govey.service.surveys.application;

import com.govey.controller.users.surveys.SurveyRequest;
import com.govey.controller.users.surveys.SurveyUpdateRequest;
import com.govey.service.surveys.domain.*;
import com.govey.service.surveys.infrastructure.*;
import com.govey.service.users.domain.User;
import com.govey.service.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyBookmarkService {
    private final SurveyRepository surveyRepository;
    private final SurveyRewardRepository surveyRewardRepository;
    private final SurveyBookmarkRepository surveyBookmarkRepository;
    private final SurveyTagRepository surveyTagRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<SurveyBookmark> page(int page, int limit) {
        return surveyBookmarkRepository.findAll(
                PageRequest.of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional()
    public Optional<SurveyBookmark> retrieve(UUID id) {
        return surveyBookmarkRepository.findById(id);
    }

    @Transactional()
    public SurveyBookmark add(User user, UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).get();
        SurveyBookmark entity = surveyBookmarkRepository.save(SurveyBookmark.builder()
                .user(user)
                .survey(survey)
                .build());

        return surveyBookmarkRepository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        SurveyBookmark result = surveyBookmarkRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        surveyBookmarkRepository.save(result);
    }
}
