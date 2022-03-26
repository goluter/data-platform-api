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

public class survey_reward {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="id")
    private Survey survey_id;

    private String type;
    private String description;

    @ManyToOne
    @JoinColumn(name="id")
    private User to;
}
