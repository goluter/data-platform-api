package com.govey.domain.survey;

import com.govey.domain.user.Tag;
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

public class SurveyTag {
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

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tagId;

    @Builder
    public SurveyTag(Date createdAt,
                     Date updatedAt,
                     Date deletedAt,
                     Long id,
                     Survey surveyId,
                     Tag tagId) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.surveyId = surveyId;
        this.tagId = tagId;
    }
}
