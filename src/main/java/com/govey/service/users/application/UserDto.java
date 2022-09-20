package com.govey.service.users.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
   @NotNull
   @Size(min = 1, max = 50)
   private String username;

   @NotNull
   @Size(min = 3, max = 100)
   private String password;

   @NotNull
   @Size(min = 1, max = 50)
   private String nickname;

   private String name;

   private String email;

   private String phoneNumber;

   private String gender;

   @JsonFormat(shape = JsonFormat.Shape.STRING)
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   private LocalDateTime birthday;
}