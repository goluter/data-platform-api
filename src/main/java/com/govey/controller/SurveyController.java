package com.govey.controller;

import com.govey.domain.survey.Survey;
import com.govey.domain.user.User;
import com.govey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/survey")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping
    public List<Survey> getSurvey() {
        return surveyService.getSurvey();
    }

    @GetMapping("{id}")
    public ResponseEntity<Survey> retrieve(@PathVariable Long id) {
        return ResponseEntity.ok(surveyService.retrieve(id));
    }

    @PostMapping
    public ResponseEntity<Survey> registerNewSurvey(@RequestBody Survey survey) {
        surveyService.addNewSurvey(survey);

        return ResponseEntity.badRequest().build();
    }

    // TODO request Body 로 Req 객체
    @PutMapping(path = "{surveyId}")
    public ResponseEntity<?> updateSurvey(
            @PathVariable("surveyId") Long survey_id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) User author_id) {
        surveyService.updateSurvey(survey_id, title, author_id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{surveyId}")
    public ResponseEntity<?> deleteSurvey(@PathVariable("surveyId") Long survey_id) {
        surveyService.deleteSurvey(survey_id);

        return ResponseEntity.ok().build();
    }
}