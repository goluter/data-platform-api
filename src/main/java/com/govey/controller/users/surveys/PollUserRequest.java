package com.govey.controller.users.surveys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PollUserRequest {
    @NotNull
    @Size(min = 1, max = 1000)
    @ApiModelProperty(example = "jy@gmail.com", notes = "", required = true)
    private String mainFeature;

    @NotNull
    @Size(min = 1, max = 1000)
    @ApiModelProperty(example = "jy@gmail.com,INTJ", notes = "main feature 를 포함한 모든 고유 정보", required = true)
    private String features;

    @NotNull
    @Size(min = 1, max = 1000)
    @ApiModelProperty(example = "예", notes = "설문 응답", required = true)
    private String value;
}
