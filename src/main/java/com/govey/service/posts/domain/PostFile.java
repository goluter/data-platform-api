package com.govey.service.posts.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PostFile extends BaseEntity {
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn
    @JsonIncludeProperties(value = {"id"})
    private Post post;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    @JsonIncludeProperties(value = {"id"})
    private FileEntity file;
}
