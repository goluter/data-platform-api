package com.govey.controller.users.surveys;

import com.govey.service.surveys.domain.PollType;
import com.govey.service.surveys.domain.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyStatisticResponse {
    public UUID id;

    public Survey survey;

    public String subject;

    public String description;

    public String content;

    public Boolean duplicable;

    public PollType type;

    public Boolean mandatory;

    public Integer priority;

    public List<SurveyStatisticGroup> groups;
}

