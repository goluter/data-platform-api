package com.govey.service.surveys.application;

import com.govey.controller.users.surveys.SurveyRequest;
import com.govey.controller.users.surveys.SurveyUpdateRequest;
import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveySpecification;
import com.govey.service.surveys.domain.SurveyStatus;
import com.govey.service.surveys.domain.SurveyTag;
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
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyRewardRepository surveyRewardRepository;
    private final SurveyBookmarkRepository surveyBookmarkRepository;
    private final SurveyTagRepository surveyTagRepository;
    private final PollRepository pollRepository;
    private final PollItemRepository pollItemRepository;
    private final PollAnswerRepository pollIAnswerRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<Survey> page(User reader, int page, int limit, Optional<String> subject, Optional<List<SurveyStatus>> statuses) {
        Specification<Survey> specification = Specification.where(SurveySpecification.find(reader, subject, statuses));
        return surveyRepository.findAll(
                specification,
                PageRequest
                        .of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional()
    public Optional<Survey> retrieve(UUID id, Optional<User> reader) {
        Optional<Survey> survey = surveyRepository.findById(id);
        return survey;
    }

    @Transactional()
    public Survey add(User author, SurveyRequest dto) {
        Survey entity = surveyRepository.save(Survey.builder()
                .subject(dto.getSubject())
                .content(dto.getContent())
                .startAt(dto.getStartAt())
                .endAt(dto.getEndAt())
                .author(author.getNickname())
                .user(author)
                // TODO 초기 상태 변경
//                .status(SurveyStatus.pending)
                .status(SurveyStatus.ongoing)
                .build());

        if (dto.getTarget() != null) {
            // TODO: 타겟 엔티티로 받도록 수정
            entity.setTarget(dto.getTarget());
        }

        if (dto.getTags() != null && dto.getTags().size() > 0) {
            entity.setTag("#" + String.join(" #", dto.getTags()));
            dto.getTags().forEach((tag) -> {
                surveyTagRepository.save(SurveyTag.builder()
                        .survey(entity)
                        .value(tag)
                        .build());
            });
        }

        return surveyRepository.save(entity);
    }

    @Transactional()
    public Survey update(UUID id, SurveyUpdateRequest request) throws IllegalStateException {
        Survey entity = surveyRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        if (request.getSubject() != null) {
            entity.setSubject(request.getSubject());
        }
        if (request.getContent() != null) {
            entity.setContent(request.getContent());
        }
        if (request.getStatus() != null) {
            if (entity.getStatus().equals(SurveyStatus.end)) {
                throw new IllegalStateException("이미 종료된 투표입니다.");
            }
            entity.setStatus(request.getStatus());
            if (request.getStatus().equals(SurveyStatus.end)) {
                entity.setCompletedAt(LocalDateTime.now());
            }
        }

        return surveyRepository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        Survey result = surveyRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        surveyRepository.save(result);
    }
}
