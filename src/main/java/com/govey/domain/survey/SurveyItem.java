package com.govey.domain.survey;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class SurveyItem {
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey surveyId;

    private Boolean mandatory;
    private String value;
    private String type;

    @Builder
    public SurveyItem(Date createdAt,
                      Date updatedAt,
                      Date deletedAt,
                      Long id,
                      Survey survey_id,
                      Boolean mandatory,
                      String value,
                      String type) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.surveyId = survey_id;
        this.mandatory = mandatory;
        this.value = value;
        this.type = type;
    }
}
