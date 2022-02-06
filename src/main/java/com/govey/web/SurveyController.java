package com.govey.web;

import com.govey.domain.survey.Survey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/survey")
public class SurveyController {
    @GetMapping
    public List<Survey> getSurvey(){
        return List.of(
                new Survey(
                        "test title",
                        "test author",
                        LocalDate.of(2000, Month.JANUARY, 5),
                        LocalDate.of(2021, Month.JANUARY, 1),
                        "test target",
                        123,
                        1000
                )
        );
    }
}