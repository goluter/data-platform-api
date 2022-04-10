package com.govey.domain.blog;

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

public class Blog {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Blog parent_id;

    private String subject;
    private String content;
    private Boolean is_comment;

    private Long hit_count;
    private Long good_count;
    private Long no_good_count;
}
