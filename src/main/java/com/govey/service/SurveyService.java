package com.govey.service;

import com.govey.domain.survey.Survey;
import com.govey.domain.survey.SurveyRepository;
import com.govey.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public List<Survey> getSurvey() {
        return surveyRepository.findAll();
    }

    public Survey retrieve(Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void addNewSurvey(Survey survey) {
        Optional<Survey> surveyOptional = surveyRepository.findByTitle(survey.getTitle());
        if (surveyOptional.isPresent()){
            throw new IllegalStateException("title taken");
        }
        surveyRepository.save(survey);
    }

    public void deleteSurvey(Long survey_id) {
        boolean exists = surveyRepository.existsById(survey_id);
        if (!exists) {
            throw new IllegalStateException("survey id " + survey_id + " does not exists");
        }
        surveyRepository.deleteById(survey_id);
    }

    @Transactional
    public void updateSurvey(Long survey_id, String title, User author_id) {
        Survey survey = surveyRepository.findById(survey_id)
                .orElseThrow(() -> new IllegalStateException(
                        "survey with id " + survey_id + " does not exists")
                );
        if (title != null &&
                title.length() > 0 &&
                !Objects.equals(survey.getTitle(), title)) {
            survey.setTitle(title);
        }
        if (author_id != null &&
                !Objects.equals(survey.getAuthorId(), author_id)) {
            Optional<Survey> surveyOptional = surveyRepository.findByAuthorId(author_id);
            if (surveyOptional.isPresent()) {
                throw new IllegalStateException("Author Taken");
            }
            survey.setAuthorId(author_id);
        }
    }
}
