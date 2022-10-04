package com.govey.service.surveys.application;

import com.govey.controller.users.surveys.PollUserRequest;
import com.govey.service.surveys.domain.*;
import com.govey.service.surveys.infrastructure.*;
import com.govey.service.users.application.UserPointService;
import com.govey.service.users.application.UserTimelineService;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserTimeline;
import com.govey.service.users.domain.UserTimelineType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollUserService {
    private final SurveyRepository surveyRepository;
    private final PollRepository pollRepository;
    private final PollUserRepository pollUserRepository;
    private final UserTimelineService userTimelineService;
    private final UserPointService userPointService;
    private final SurveyUserService surveyUserService;
    private final SurveyRewardService surveyRewardService;

    @Transactional()
    public List<PollUser> listByPollId(UUID pollId) {
        Poll poll = pollRepository.findById(pollId).get();
        return pollUserRepository.findAllByPoll(poll);
    }

    @Transactional()
    public PollUser add(UUID pollId, Optional<User> user, PollUserRequest dto) {
        Poll poll = pollRepository.findById(pollId).get();
        Survey survey = surveyRepository.findById(poll.getSurvey().getId()).get();
        if (!survey.getStatus().equals(SurveyStatus.ongoing)) {
            throw new RuntimeException("진행중인 투표가 아닙니다.");
        }

        PollUser newPollUser = PollUser.builder()
                .poll(poll)
                .value(dto.getValue())
                .mainFeature(dto.getMainFeature())
                .features(dto.getFeatures())
                .build();
        
        if (user.isPresent()) {
            Optional<PollUser> existingPollUser = pollUserRepository.findByPollAndUser(poll, user.get());
            // NOTE: 이미 투표가 존재하는 경우 업데이트
            if (existingPollUser.isPresent()) {
                PollUser updatingPollUser = existingPollUser.get();
                updatingPollUser.setValue(dto.getValue());
                updatingPollUser.setMainFeature(dto.getMainFeature());
                updatingPollUser.setFeatures(dto.getFeatures());
                return pollUserRepository.save(updatingPollUser);
            }
            // NOTE: 새로 생성
            else {
                newPollUser.setUser(user.get());
                survey.setAnswers(survey.getAnswers() + 1);
                surveyRepository.save(survey);
                return pollUserRepository.save(newPollUser);
            }
        } else {
            survey.setAnswers(survey.getAnswers() + 1);
            surveyRepository.save(survey);
            return pollUserRepository.save(newPollUser);
        }
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
