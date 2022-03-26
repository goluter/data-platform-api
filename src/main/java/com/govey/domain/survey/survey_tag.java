package com.govey.domain.survey;

import com.govey.domain.user.Tag;
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

public class survey_tag {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="id")
    private Survey survey_id;

    @ManyToOne
    @JoinColumn(name="id")
    private Tag tag_id;

    @Builder
    public survey_tag(LocalDate created_at,
                      LocalDate updated_at,
                      LocalDate deleted_at,
                      Long id,
                      Survey survey_id,
                      Tag tag_id) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.survey_id = survey_id;
        this.tag_id = tag_id;
    }
}
