package com.govey.controller.users.surveys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.govey.service.surveys.domain.SurveyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyUpdateRequest {
    @Size(min = 1, max = 1000)
    private String subject;

    @Size(min = 1)
    private String content;

    private String target;

    private Boolean isPopular;

    private Boolean isRecommended;

    private List<String> tags;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endAt;

    private SurveyStatus status;

    private String imageUrl;
}
