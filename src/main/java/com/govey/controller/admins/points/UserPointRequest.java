package com.govey.controller.admins.points;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.govey.service.users.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPointRequest {
    private String title;

    private String content;

    private Integer amount;

    private UUID receiverId;
}
