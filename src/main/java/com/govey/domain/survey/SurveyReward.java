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

public class SurveyReward {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Survey survey_id;

    private String type;
    private String description;

    @ManyToOne
    private User to;

    @Builder
    public SurveyReward(LocalDate created_at,
                        LocalDate updated_at,
                        LocalDate deleted_at,
                        Long id,
                        Survey survey_id,
                        String type,
                        String description,
                        User to) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.survey_id = survey_id;
        this.type = type;
        this.description = description;
        this.to = to;
    }
}
