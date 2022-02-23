package com.govey.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

//    @Query("SELECT s FROM Survey s WHERE s.title = ?1")
    Optional<Survey> findSurveyByTitle(String title);
    Optional<Survey> findSurveyByAuthor(String author);
}
