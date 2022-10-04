package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.PollUser;
import com.govey.service.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PollUserRepository extends JpaRepository<PollUser, UUID> {
    List<PollUser> findAllByPoll(Poll poll);

    Optional<PollUser> findByPollAndUser(Poll poll, User user);
}
