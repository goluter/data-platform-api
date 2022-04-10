package com.govey.domain.survey;

import com.govey.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

//    @Query("SELECT s FROM Survey s WHERE s.title = ?1")
    Optional<Survey> findByTitle(String title);
    Optional<Survey> findById(Long id);
    Optional<Survey> findByAuthorId(User authorId);
}
