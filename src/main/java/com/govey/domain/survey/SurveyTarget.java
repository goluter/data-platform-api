package com.govey.domain.survey;

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

public class SurveyTarget {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Survey survey_id;

    private String type;
    private Long target_id;

    @Builder
    public SurveyTarget(LocalDate created_at,
                        LocalDate updated_at,
                        LocalDate deleted_at,
                        Long id,
                        Survey survey_id,
                        String type,
                        Long target_id) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.survey_id = survey_id;
        this.type = type;
        this.target_id = target_id;
    }
}
