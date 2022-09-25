package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyBookmark;
import com.govey.service.surveys.domain.SurveyReward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurveyRewardRepository extends JpaRepository<SurveyReward, UUID> {
    Page<SurveyReward> findAll(Pageable pageable);
}
