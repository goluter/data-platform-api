package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyReward;
import com.govey.service.surveys.domain.SurveyTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurveyTagRepository extends JpaRepository<SurveyTag, UUID> {
    Page<SurveyTag> findAll(Pageable pageable);
}
