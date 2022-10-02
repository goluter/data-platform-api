package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.SurveyBookmark;
import com.govey.service.surveys.domain.SurveyUser;
import com.govey.service.users.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyUserRepository extends JpaRepository<SurveyUser, UUID> {
    Page<SurveyUser> findAllByUser(User user, Pageable pageable);

    Optional<SurveyUser> findBySurveyAndUser(Survey survey, User user);
}
