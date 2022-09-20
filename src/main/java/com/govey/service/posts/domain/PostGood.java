package com.govey.service.posts.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import com.govey.service.users.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PostGood extends BaseEntity {
    @ManyToOne
    @JoinColumn
    @JsonIncludeProperties(value = {"id"})
    private Post post;

    @ManyToOne
    @JoinColumn
    @JsonIncludeProperties(value = {"id"})
    private User user;
}
