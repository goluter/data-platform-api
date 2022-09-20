package com.govey.controller.users.surveys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PollUserRequest {
    @Size(min = 1, max = 1000)
    private String value;
}
