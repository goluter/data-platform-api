package com.govey.controller.admins.notifications;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
   private String title;

   private String description;

   private UUID userId;
}