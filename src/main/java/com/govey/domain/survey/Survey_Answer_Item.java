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

public class Survey_Answer_Item {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="id")
    private Survey_Item survey_item_id;

    private String type;
    private String value;

    @Builder
    public Survey_Answer_Item(LocalDate created_at,
                              LocalDate updated_at,
                              LocalDate deleted_at,
                              Long id,
                              Survey_Item survey_item_id,
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
