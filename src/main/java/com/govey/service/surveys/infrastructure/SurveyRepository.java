package com.govey.service.surveys.infrastructure;

import com.govey.service.surveys.domain.Survey;
import com.govey.service.users.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, UUID> {
    Page<Survey> findAllByUser(User user, Pageable pageable);

    List<Survey> findAllByIsPopular(Boolean isPopular);

    List<Survey> findAllByIsRecommended(Boolean isRecommended);

    Page<Survey> findAll(Specification<Survey> spec, Pageable pageable);
}
