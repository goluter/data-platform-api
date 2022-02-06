package com.govey.domain.survey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class SurveyConfig {
    @Bean
    CommandLineRunner commandLineRunner(SurveyRepository repo) {
        return args -> {
            Survey testSurvey = new Survey(
                    "test title",
                    "test author",
                    LocalDate.of(2000, JANUARY, 5),
                    LocalDate.of(2021, JANUARY, 1),
                    "test target",
                    123,
                    1000
            );
            Survey testSurvey2 = new Survey(
                    "test title2",
                    "test author2",
                    LocalDate.of(2001, JANUARY, 5),
                    LocalDate.of(2022, JANUARY, 1),
                    "test target2",
                    124,
                    1001
            );
            repo.saveAll(
                    List.of(testSurvey, testSurvey2)
            );
        };
    }
}
