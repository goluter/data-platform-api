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

public class survey_answer_item {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="id")
    private survey_item survey_item_id;

    private String type;
    private String value;

    @Builder
    public survey_answer_item(LocalDate created_at,
                         LocalDate updated_at,
                         LocalDate deleted_at,
                         Long id,
                         survey_item survey_item_id,
                         String type,
                         String value) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.survey_item_id = survey_item_id;
        this.type = type;
        this.value = value;
    }
}
