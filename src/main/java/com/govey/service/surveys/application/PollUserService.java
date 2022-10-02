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

        if (user.isPresent()) {
            if (pollUserRepository.findAllByPollAndUser(poll, user.get()).size() != 0) {
                throw new RuntimeException("이미 투표하셨습니다.");
            }
            if (!surveyUserService.checkDuplication(user.get(), poll.getSurvey().getId())) {
                // NOTE: 횟수 추가
                survey.setAnswers(survey.getAnswers()+1);
                surveyRepository.save(survey);
                surveyUserService.add(user.get(), survey.getId());

                // NOTE: 타임라인 등록
                userTimelineService.add(UserTimeline.builder()
                        .user(user.get())
                        .title(String.format("서베이 참여"))
                        .content(String.format("\"%s\"서베이에 참여하셨습니다!", survey.getSubject()))
                        .type(UserTimelineType.survey)
                        .imageUrl("")
                        .link(String.format("/surveys/view?id=%s", survey.getId()))
                        .build());

                // NOTE: 보상 지급
                Page<SurveyReward> rewardPage =  surveyRewardService.page(survey.getId(), 0, 10000);
                List<SurveyReward> pointRewards = rewardPage.getContent().stream().filter((reward) -> {
                    return reward.getType().equals(SurveyRewardType.point);
                }).collect(Collectors.toList());
                if (pointRewards.size() > 0) {
                    pointRewards.forEach((reward -> {
                        userPointService.add(String.format("\"%s\"설문 참여 리워드 획득", survey.getSubject()), String.format("설문에 참여하여 포인트 획득하셨습니다."), Integer.parseInt(reward.getValue()), user.get().getId());
                    }));
                }
            }
        } else {
            survey.setAnswers(survey.getAnswers()+1);
            surveyRepository.save(survey);
        }

        PollUser pollUser = PollUser.builder()
                .poll(poll)
                .value(dto.getValue())
                .mainFeature(dto.getMainFeature())
                .features(dto.getFeatures())
                .build();

        if (user.isPresent()) {
            pollUser.setUser(user.get());
        }

        return pollUserRepository.save(pollUser);
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
