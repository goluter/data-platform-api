package com.govey.service.surveys.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Builder
@DynamicInsert
@Where(clause = "deleted=false")
public class Poll extends BaseEntity {
    @ManyToOne()
    @JoinColumn(nullable = false)
    @JsonIncludeProperties(value = {"id"})
    private Survey survey;

    @Column(nullable = false)
    private String subject;

    @Column
    @ColumnDefault("")
    private String description;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    @ColumnDefault("false")
    private Boolean duplicable;

    @Column(nullable = false)
    private PollType type;

    @Column
    @ColumnDefault("false")
    private Boolean mandatory;
}