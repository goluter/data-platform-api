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

public class survey_item {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="id")
    private Survey survey_id;

    private Boolean mandatory;
    private String value;
    private String type;

    @Builder
    public survey_item(LocalDate created_at,
                           LocalDate updated_at,
                           LocalDate deleted_at,
                           Long id,
                           Survey survey_id,
                           Boolean mandatory,
                           String value,
                           String type) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.survey_id = survey_id;
        this.mandatory = mandatory;
        this.value = value;
        this.type = type;
    }
}
