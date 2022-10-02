package com.govey.controller.users.surveys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyStatisticGroup {
    public String name;

    public Integer count;
}
