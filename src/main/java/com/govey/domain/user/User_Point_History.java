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

public class User_Point_History {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="id")
    private User user_id;

    private String description;
    private Long value;
    private Long result;

    @Builder
    public User_Point_History(LocalDate created_at,
                              LocalDate updated_at,
                              LocalDate deleted_at,
                              Long id,
                              User user_id,
                              String description,
                              Long value,
                              Long result) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.user_id = user_id;
        this.description = description;
        this.value = value;
        this.result = result;
    }
}
