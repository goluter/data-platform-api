package com.govey.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class Title {
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    @Column(name = "condition_description")
    private String conditionDescription;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Title parentId;

    private Long level;

    @Builder
    public Title(Date createdAt,
                 Date updatedAt,
                 Date deletedAt,
                 Long id,
                 String description,
                 String conditionDescription,
                 Title parentId,
                 Long level) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.description = description;
        this.conditionDescription = conditionDescription;
        this.parentId = parentId;
        this.level = level;
    }
}