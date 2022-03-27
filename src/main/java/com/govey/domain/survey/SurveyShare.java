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

public class SurveyShare {
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
    private User user_id;

    private String type;
    private String url;
    private Long open_count;

    @Builder
    public SurveyShare(LocalDate created_at,
                       LocalDate updated_at,
                       LocalDate deleted_at,
                       Long id,
                       Survey survey_id,
                       User user_id,
                       String type,
                       String url,
                       Long open_count) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.survey_id = survey_id;
        this.user_id = user_id;
        this.type = type;
        this.url = url;
        this.open_count = open_count;
    }
}