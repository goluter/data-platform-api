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
    @ApiModelProperty(example = "칭호", notes = "보상 종류", required = true)
    private String type;

    @Size(min = 1, max = 1000)
    @ApiModelProperty(example = "나는 대학생이다.", notes = "보상 이름", required = true)
    private String name;

    @Size(min = 0, max = 1000)
    @ApiModelProperty(example = "상명대학교 학생!", notes = "보상 설명", required = true)
    private String content;

    @Size(min = 0, max = 1000)
    @ApiModelProperty(example = "상명대학교 인증 링크를 통해 획득할 수 있습니다.", notes = "보상 획득 조건", required = true)
    private String requirements;

    @ApiModelProperty(example = "0", notes = "기준 값", required = true)
    private Integer value;

    private String imageUrl;

    @ApiModelProperty(example = "0", notes = "획득한 유저 수", required = true)
    private Integer count;
}
