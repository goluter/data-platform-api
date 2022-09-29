package com.govey.controller.users.surveys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportRequest {
    private String subject;

    private String content;

    private String category;

    private Boolean isNotice;
}
