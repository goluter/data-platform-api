package com.govey.service;

import com.govey.domain.survey.Survey;
import com.govey.domain.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public List<Survey> getSurvey() {
        return surveyRepository.findAll();
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
        } surveyRepository.deleteById(surveyId);
    }
}
