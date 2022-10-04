package com.govey.service.surveys.application;

import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyReward;
import com.govey.service.surveys.domain.SurveyRewardType;
import com.govey.service.surveys.domain.SurveyUser;
import com.govey.service.surveys.infrastructure.SurveyUserRepository;
import com.govey.service.surveys.infrastructure.SurveyRepository;
import com.govey.service.surveys.infrastructure.SurveyRewardRepository;
import com.govey.service.surveys.infrastructure.SurveyTagRepository;
import com.govey.service.users.application.UserPointService;
import com.govey.service.users.application.UserTimelineService;
import com.govey.service.users.domain.User;
import com.govey.service.users.domain.UserTimeline;
import com.govey.service.users.domain.UserTimelineType;
import com.govey.service.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.LockMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyUserService {
    private final SurveyRepository surveyRepository;
    private final SurveyRewardRepository surveyRewardRepository;
    private final SurveyUserRepository surveyUserRepository;
    private final SurveyTagRepository surveyTagRepository;
    private final UserRepository userRepository;
    private final UserTimelineService userTimelineService;
    private final SurveyRewardService surveyRewardService;
    private final UserPointService userPointService;

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
    public Optional<SurveyUser> retrieve(User user, UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).get();
        return surveyUserRepository.findBySurveyAndUser(survey, user);
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
            throw new RuntimeException("이미 참여하셨습니다.");
        }

        // TODO: 모든 poll 을 제출했는지 여부를 체크하고 하지 않았으면 에러 표시해야함.

        SurveyUser entity = surveyUserRepository.save(SurveyUser.builder()
                .user(user)
                .survey(survey)
                .build());

        // NOTE: 횟수 추가
        survey.setAnswers(survey.getAnswers()+1);
        surveyRepository.save(survey);

        // NOTE: 타임라인 등록
        userTimelineService.add(UserTimeline.builder()
                .user(user)
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
                userPointService.add(String.format("\"%s\"설문 참여 리워드 획득", survey.getSubject()), String.format("설문에 참여하여 포인트 획득하셨습니다."), Integer.parseInt(reward.getValue()), user.getId());
            }));
        }

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
