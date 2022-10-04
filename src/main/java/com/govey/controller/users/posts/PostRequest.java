package com.govey.controller.users.posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRequest {
    private String subject;

    private String content;

    private String category;

    private Boolean isNotice;

    private String imageUrl;
}
