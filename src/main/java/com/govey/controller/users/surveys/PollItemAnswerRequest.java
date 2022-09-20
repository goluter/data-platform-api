package com.govey.controller.users.surveys;

import com.govey.service.surveys.domain.PollItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PollItemAnswerRequest {
    private String pollItemId;

    private String content;

    private PollItemType type;
}
