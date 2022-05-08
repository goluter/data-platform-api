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
public class SurveyReward {
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

    private String type;
    private String description;

    @ManyToOne
    @JoinColumn(name = "reward_to")
    private User rewardTo;

    @Builder
    public SurveyReward(Date createdAt,
                        Date updatedAt,
                        Date deletedAt,
                        Long id,
                        Survey surveyId,
                        String type,
                        String description,
                        User rewardTo) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.surveyId = surveyId;
        this.type = type;
        this.description = description;
        this.rewardTo = rewardTo;
    }
}
