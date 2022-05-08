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

public class SurveyAnswer {
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

    private String status;

    @Builder
    public SurveyAnswer(Date createdAt,
                        Date updatedAt,
                        Date deletedAt,
                        Long id,
                        Survey survey_id,
                        User user_id,
                        String status) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.surveyId = survey_id;
        this.userId = user_id;
        this.status = status;
    }
}
