package com.govey.service.surveys.application;

import com.govey.controller.users.surveys.PollItemRequest;
import com.govey.controller.users.surveys.PollRequest;
import com.govey.service.surveys.domain.*;
import com.govey.service.surveys.infrastructure.*;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PollService {
    private final SurveyRepository surveyRepository;
    private final PollRepository pollRepository;
    private final PollItemRepository pollItemRepository;
    private final PollAnswerRepository pollAnswerRepository;
    private final PollUserRepository pollUserRepository;

    @Transactional()
    public List<Poll> findAllBySurvey(UUID id) {
        Survey survey = surveyRepository.findById(id).get();
        return pollRepository.findAllBySurvey(survey);
    }

    @Transactional()
    public List<Poll> list() {
        return pollRepository.findAll();
    }

    @Transactional()
    public List<PollItem> listPollItems(UUID pollId) {
        Poll poll = pollRepository.findById(pollId).get();
        return pollItemRepository.findAllByPoll(poll);
    }

    @Transactional()
    public List<PollAnswer> listMyAnswers(User user, Poll poll) {
        return pollAnswerRepository.findAllByPollAndUser(poll, user);
    }

    @Transactional()
    public List<PollAnswer> listAnswerByItem(UUID itemId) {
        PollItem item = pollItemRepository.findById(itemId).get();
        return pollAnswerRepository.findAllByPollItem(item);
    }

    @Transactional()
    public List<PollAnswer> listAnswerByPoll(UUID pollId) {
        Poll item = pollRepository.findById(pollId).get();
        return pollAnswerRepository.findAllByPoll(item);
    }

    @Transactional()
    public Optional<Poll> retrieve(UUID id) {
        return pollRepository.findById(id);
    }

    @Transactional()
    public Poll add(UUID surveyId, PollRequest dto) {
        Survey survey = surveyRepository.findById(surveyId).get();

        Poll poll = Poll.builder()
                .survey(survey)
                .subject(dto.getSubject())
                .content(dto.getContent())
                .duplicable(dto.getDuplicable())
                .type(dto.getType())
                .build();

        poll.setId(UUID.randomUUID());

        return pollRepository.save(poll);
    }

    @Transactional()
    public PollItem add(UUID id, PollItemRequest dto) {
        Poll poll = pollRepository.findById(id).get();

        PollItem item = PollItem.builder()
                .poll(poll)
                .subject(dto.getSubject())
                .content(dto.getContent())
                .type(dto.getType())
                .build();

        item.setId(UUID.randomUUID());

        return pollItemRepository.save(item);
    }

    @Transactional()
    public PollAnswer answer(User user, UUID pollItemId) {
        PollItem item = pollItemRepository.findById(pollItemId).get();
        Poll poll = item.getPoll();

        // NOTE: 기존에 있으면 막기
        List<PollAnswer> answers = pollAnswerRepository.findAllByPollAndUser(poll, user);
        if (answers.size() > 0 && !poll.getDuplicable()) {
            throw new IllegalStateException("Already vote the poll");
        }

        PollAnswer entity = PollAnswer.builder()
                .poll(poll)
                .pollItem(item)
                .user(user)
                .build();

        entity.setId(UUID.randomUUID());

        return pollAnswerRepository.save(entity);
    }

    @Transactional()
    public Poll update(UUID id, Poll request) {
        Poll entity = pollRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );

        Survey survey = entity.getSurvey();
        if (!survey.getStatus().equals(SurveyStatus.pending)) {
            throw new IllegalStateException("펜딩 중인 투표만 수정이 가능합니다.");
        }

        if (request.getSubject() != null) {
            entity.setSubject(request.getSubject());
        }
        if (request.getContent() != null) {
            entity.setContent(request.getContent());
        }

        return pollRepository.save(entity);
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
