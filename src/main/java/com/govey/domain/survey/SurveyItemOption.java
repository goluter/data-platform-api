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

public class SurveyItemOption {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="survey_item_id")
    private SurveyItem surveyItemId;

    private String value;

    @Builder
    public SurveyItemOption(LocalDate created_at,
                            LocalDate updated_at,
                            LocalDate deleted_at,
                            Long id,
                            SurveyItem survey_item_id,
                            String value) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.surveyItemId = survey_item_id;
        this.value = value;
    }
}
