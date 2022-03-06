package com.govey.service;

import com.govey.domain.survey.Survey;
import com.govey.domain.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<Survey> surveyOptional = surveyRepository.findSurveyByTitle(survey.getTitle());
        if (surveyOptional.isPresent()){
            throw new IllegalStateException("title taken");
        }
        surveyRepository.save(survey);
    }

    public void deleteSurvey(Long surveyId) {
        boolean exists = surveyRepository.existsById(surveyId);
        if (!exists) {
            throw new IllegalStateException("survey id " + surveyId + " does not exists");
        }
        surveyRepository.deleteById(surveyId);
    }

    @Transactional
    public void updateSurvey(Long surveyId, String title, String author) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalStateException(
                        "survey with id " + surveyId + " does not exists")
                );
        if (title != null &&
                title.length() > 0 &&
                !Objects.equals(survey.getTitle(), title)) {
            survey.setTitle(title);
        }
        if (author != null &&
                author.length() > 0 &&
                !Objects.equals(survey.getAuthor(), author)) {
            Optional<Survey> surveyOptional = surveyRepository.findSurveyByAuthor(author);
            if (surveyOptional.isPresent()) {
                throw new IllegalStateException("Author Taken");
            }
            survey.setAuthor(author);
        }
    }
}
