package com.govey.domain.survey;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class SurveyAnswerItem {
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
    @JoinColumn(name = "survey_item_id")
    private SurveyItem surveyItemId;

    private String type;
    private String value;

    @Builder
    public SurveyAnswerItem(Date createdAt,
                            Date updatedAt,
                            Date deletedAt,
                            Long id,
                            SurveyItem survey_item_id,
                            String type,
                            String value) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.surveyItemId = survey_item_id;
        this.type = type;
        this.value = value;
    }
}
