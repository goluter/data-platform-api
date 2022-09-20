package com.govey.controller.users.surveys;

import com.govey.service.surveys.domain.PollType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PollRequest {
    @Size(min = 1, max = 1000)
    private String subject;

    @Size(min = 1, max = 1000)
    private String content;

    private Boolean duplicable;

    private PollType type;
}
