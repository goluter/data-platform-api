package com.govey.service.surveys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import com.govey.service.users.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Table
@DynamicInsert
@Where(clause = "deleted=false")
public class Survey extends BaseEntity {
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIncludeProperties(value = {"id"})
    private User user;

    @Column
    private String author;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private SurveyStatus status;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startAt;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endAt;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime completedAt;


    @Column(columnDefinition = "Integer default 0")
    @ColumnDefault("0")
    private Integer goods;

    @Column(columnDefinition = "Integer default 0")
    @ColumnDefault("0")
    private Integer nogoods;

    @Column(columnDefinition = "Integer default 0")
    @ColumnDefault("0")
    private Integer hits;
}