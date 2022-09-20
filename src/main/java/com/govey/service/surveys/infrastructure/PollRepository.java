package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PollRepository extends JpaRepository<Poll, UUID> {
    List<Poll> findAllBySurvey(Survey survey);
}
