package com.govey.service.surveys.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.govey.domain.BaseEntity;
import com.govey.service.posts.domain.PostFile;
import com.govey.service.users.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table
@Builder
@Entity
@DynamicInsert
@Where(clause = "deleted=false")
public class Report extends BaseEntity {
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Survey Survey;

    @Column
    private String author;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String category;

    @Column
    @ColumnDefault("false")
    private Boolean isNotice;

    @Column
    @ColumnDefault("0")
    private Integer goods;

    @Column
    @ColumnDefault("0")
    private Integer nogoods;

    @Column
    @ColumnDefault("0")
    private Integer hits;

    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PostFile> files;
}