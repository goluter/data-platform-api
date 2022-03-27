package com.govey.web;

import com.govey.domain.survey.Survey;
import com.govey.domain.user.User;
import com.govey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// @CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/survey")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping
    public List<Survey> getSurvey(){
//        List<Survey> list = surveyService.getSurvey();
//        // todo reponse
//        List<SurveyResponse> listA = list.stream().map(SurveyResponse::new)
//                .collect(Collectors.toList());
        return surveyService.getSurvey();
    }

    @GetMapping("{id}")
    public ResponseEntity<Survey> retrieve(@PathVariable String id){
        return ResponseEntity.ok(surveyService.retrieve(1L));
    }

    @PostMapping
    public ResponseEntity<Survey> registerNewSurvey(@RequestBody Survey survey) {
        surveyService.addNewSurvey(survey);

        return ResponseEntity.badRequest().build();
    }

    // TODO request Body 로 Req 객체
    @PutMapping(path = "{surveyId}")
    public ResponseEntity<?> updateSurvey(
            @PathVariable("surveyId") Long surveyId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) User author_id,
            LocalDate updated_at) {
        surveyService.updateSurvey(surveyId, title, author_id, updated_at);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{surveyId}")
    public ResponseEntity<?> deleteSurvey(@PathVariable("surveyId") Long surveyId) {
        surveyService.deleteSurvey(surveyId);

        return ResponseEntity.ok().build();
    }
}