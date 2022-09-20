package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.PollAnswer;
import com.govey.service.surveys.domain.PollItem;
import com.govey.service.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PollAnswerRepository extends JpaRepository<PollAnswer, UUID> {
    List<PollAnswer> findAllByPollAndUser(Poll poll, User user);

    List<PollAnswer> findAllByPoll(Poll poll);

    List<PollAnswer> findAllByPollItem(PollItem poll);
}
