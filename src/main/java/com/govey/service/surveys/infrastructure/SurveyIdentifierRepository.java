package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyIdentifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface SurveyIdentifierRepository extends JpaRepository<SurveyIdentifier, UUID> {
    List<SurveyIdentifier> findAllBySurvey(Survey survey, Sort sort);
}
