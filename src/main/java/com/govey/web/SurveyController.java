package com.govey.web;

import com.govey.domain.survey.Survey;
import com.govey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping
    public List<Survey> getSurvey(){
        return surveyService.getSurvey();
    }
    @PostMapping
    public void registerNewSurvey(@RequestBody Survey survey) {
        surveyService.addNewSurvey(survey);
    }
    @DeleteMapping(path = "{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") Long surveyId) {
        surveyService.deleteSurvey(surveyId);
    }
}