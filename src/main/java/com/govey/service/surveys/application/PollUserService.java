package com.govey.service.surveys.application;

import com.govey.controller.users.surveys.PollUserRequest;
import com.govey.service.surveys.domain.*;
import com.govey.service.surveys.infrastructure.*;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PollUserService {
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
    public PollUser add(UUID pollId, User user, PollUserRequest dto) {
        Poll poll = pollRepository.findById(pollId).get();
        Survey survey = surveyRepository.findById(poll.getSurvey().getId()).get();
        if (!survey.getStatus().equals(SurveyStatus.ongoing)) {
            throw new RuntimeException("진행중인 투표가 아닙니다.");
        }
        if (pollUserRepository.findAllByPollAndUser(poll, user).size() != 0) {
            throw new RuntimeException("이미 투표하셨습니다.");
        }

        PollUser pollUser = PollUser.builder()
                .poll(poll)
                .user(user)
                .value(dto.getValue())
                .build();

        pollUser.setId(UUID.randomUUID());

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
