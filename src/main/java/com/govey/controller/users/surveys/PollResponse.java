package com.govey.controller.users.surveys;

import com.govey.service.surveys.domain.Poll;
import com.govey.service.surveys.domain.PollUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PollResponse extends Poll {
   private List<PollUser> answers;
}
