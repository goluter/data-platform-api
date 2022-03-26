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

public class survey_comments {
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

    @ManyToOne
    @JoinColumn(name="id")
    private survey_comments parent_id;

    private String value;
    private Long good_count;
    private Long no_good_count;

    @Builder
    public survey_comments(LocalDate created_at,
                           LocalDate updated_at,
                           LocalDate deleted_at,
                           Long id,
                           Survey survey_id,
                           User user_id,
                           survey_comments parent_id,
                           String value,
                           Long good_count,
                           Long no_good_count) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.survey_id = survey_id;
        this.user_id = user_id;
        this.parent_id = parent_id;
        this.value = value;
        this.good_count = good_count;
        this.no_good_count = no_good_count;
    }
}
