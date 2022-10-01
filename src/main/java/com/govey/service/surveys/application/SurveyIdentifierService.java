package com.govey.service.surveys.application;

import com.govey.controller.users.surveys.PollItemRequest;
import com.govey.controller.users.surveys.PollRequest;
import com.govey.controller.users.surveys.SurveyIdentifierRequest;
import com.govey.service.surveys.domain.*;
import com.govey.service.surveys.infrastructure.*;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyIdentifierService {
    private final SurveyRepository surveyRepository;
    private final SurveyIdentifierRepository surveyIdentifierRepository;

    @Transactional()
    public List<SurveyIdentifier> findAllBySurvey(UUID id) {
        Survey survey = surveyRepository.findById(id).get();
        return surveyIdentifierRepository.findAllBySurvey(survey, Sort.by("priority").descending());
    }

    @Transactional()
    public SurveyIdentifier retrieve(UUID id) {
        return surveyIdentifierRepository.findById(id).get();
    }

    @Transactional()
    public SurveyIdentifier add(UUID surveyId, SurveyIdentifierRequest dto) {
        Survey survey = surveyRepository.findById(surveyId).get();

        SurveyIdentifier entity = SurveyIdentifier.builder()
                .survey(survey)
                .subject(dto.getSubject())
                .content(dto.getContent())
                .priority(dto.getPriority())
                .mandatory(dto.getMandatory())
                .build();

        return surveyIdentifierRepository.save(entity);
    }

    @Transactional()
    public SurveyIdentifier update(UUID id, SurveyIdentifierRequest request) {
        SurveyIdentifier entity = surveyIdentifierRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );

        Survey survey = entity.getSurvey();
//        if (!survey.getStatus().equals(SurveyStatus.pending)) {
//            throw new IllegalStateException("펜딩 중인 투표만 수정이 가능합니다.");
//        }

        if (request.getSubject() != null) {
            entity.setSubject(request.getSubject());
        }
        if (request.getContent() != null) {
            entity.setContent(request.getContent());
        }
        if (request.getPriority() != null) {
            entity.setPriority(request.getPriority());
        }

        return surveyIdentifierRepository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        SurveyIdentifier result = surveyIdentifierRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        surveyIdentifierRepository.save(result);
    }
}
