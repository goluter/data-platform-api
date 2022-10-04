package com.govey.controller.users.surveys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportRequest {
    private String subject;

    private String content;

    private String category;

    private Boolean isNotice;

    private String imageUrl;
}
