package com.govey.controller.users.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordRequest {
    @Size(min = 3, max = 50)
    private String password;
}
