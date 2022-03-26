package com.govey.domain.survey;

import com.govey.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class survey_comments {
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
}
