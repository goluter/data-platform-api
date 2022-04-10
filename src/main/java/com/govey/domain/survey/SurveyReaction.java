package com.govey.domain.survey;

import com.govey.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class SurveyReaction {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="survey_id")
    private Survey surveyId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;

    private String type;

    @Builder
    public SurveyReaction(LocalDate created_at,
                          LocalDate updated_at,
                          LocalDate deleted_at,
                          Long id,
                          Survey survey_id,
                          User user_id,
                          String type) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.surveyId = survey_id;
        this.userId = user_id;
        this.type = type;
    }
}
