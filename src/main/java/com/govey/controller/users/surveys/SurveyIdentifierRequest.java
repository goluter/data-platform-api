package com.govey.controller.users.surveys;

import com.govey.service.surveys.domain.PollType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyIdentifierRequest {
    @Size(min = 1, max = 1000)
    @ApiModelProperty(example = "이메일 정보", notes = "항목 제목", required = true)
    private String subject;

    @Size(min = 0, max = 1000)
    @ApiModelProperty(example = "보상을 위한 이메일을 입력해주세요", notes = "항목 설명", required = true)
    private String content;

    private Boolean mandatory;

    @ApiModelProperty(example = "0", notes = "우선순위, 높을수록 우선", required = true)
    private Integer priority;
}
