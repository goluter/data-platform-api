package com.govey.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class Level {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    private Long value;
    private String name;
    private String description;

    @Builder
    public Level(LocalDate created_at,
                 LocalDate updated_at,
                 LocalDate deleted_at,
                 Long id,
                 Long value,
                 String name,
                 String description) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.value = value;
        this.name = name;
        this.description = description;
    }
}
