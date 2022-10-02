package com.govey.controller.users.rewards;

import com.govey.service.surveys.domain.PollType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RewardRequest {
    @Size(min = 1, max = 1000)
    @ApiModelProperty(example = "바나나를 좋아하시나요?", notes = "항목 제목", required = true)
    private String subject;

    @Size(min = 0, max = 1000)
    @ApiModelProperty(example = "솔직하게 답변해주세요.", notes = "추가 설명, 없으면 빈스트링", required = true)
    private String content;

    private Boolean duplicable;

    private PollType type;

    @ApiModelProperty(example = "0", notes = "우선순위, 높을수록 우선", required = true)
    private Integer priority;
}
