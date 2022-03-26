package com.govey.domain.survey;

import com.govey.domain.user.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class survey_tag {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="id")
    private Survey survey_id;

    @ManyToOne
    @JoinColumn(name="id")
    private Tag tag_id;
}
