package com.govey.domain.user;

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

public class Title {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private String condition_description;

    @ManyToOne
    private Title parent_id;
    private Long level;

    @Builder
    public Title(LocalDate created_at,
                 LocalDate updated_at,
                 LocalDate deleted_at,
                 Long id,
                 String description,
                 String condition_description,
                 Title parent_id,
                 Long level) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.description = description;
        this.condition_description = condition_description;
        this.parent_id = parent_id;
        this.level = level;
    }
}