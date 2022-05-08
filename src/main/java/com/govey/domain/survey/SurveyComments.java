package com.govey.domain.survey;

import com.govey.domain.user.User;
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

public class SurveyComments {
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
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private SurveyComments parentId;

    private String value;
    @Column(name="good_count")
    private Long goodCount;
    @Column(name="no_good_count")
    private Long noGoodCount;

    @Builder
    public SurveyComments(Date createdAt,
                          Date updatedAt,
                          Date deletedAt,
                          Long id,
                          Survey survey_id,
                          User user_id,
                          SurveyComments parent_id,
                          String value,
                          Long good_count,
                          Long no_good_count) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.surveyId = survey_id;
        this.userId = user_id;
        this.parentId = parent_id;
        this.value = value;
        this.goodCount = good_count;
        this.noGoodCount = no_good_count;
    }
}
