package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.PollItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PollItemRepository extends JpaRepository<PollItem, UUID> {
    List<PollItem> findAllByPoll(Poll poll);
}
