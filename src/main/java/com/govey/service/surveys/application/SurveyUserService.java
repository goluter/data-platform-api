package com.govey.service.surveys.application;

import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyUser;
import com.govey.service.surveys.infrastructure.SurveyUserRepository;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyUserService {
    private final SurveyRepository surveyRepository;
    private final SurveyRewardRepository surveyRewardRepository;
    private final SurveyUserRepository surveyUserRepository;
    private final SurveyTagRepository surveyTagRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<SurveyUser> page(User user, int page, int limit) {
        return surveyUserRepository.findAllByUser(
                user,
                PageRequest.of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional()
    public Optional<SurveyUser> retrieve(UUID id) {
        return surveyUserRepository.findById(id);
    }

    @Transactional()
    public boolean checkDuplication(User user, UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).get();

        return surveyUserRepository.findBySurveyAndUser(survey, user).isPresent();
    }

    @Transactional()
    public SurveyUser add(User user, UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).get();
        if (surveyUserRepository.findBySurveyAndUser(survey, user).isPresent()) {
            throw new RuntimeException("이미 북마크하셨습니다.");
        }

        SurveyUser entity = surveyUserRepository.save(SurveyUser.builder()
                .user(user)
                .survey(survey)
                .build());

        return surveyUserRepository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        SurveyUser result = surveyUserRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        surveyUserRepository.save(result);
    }
}
