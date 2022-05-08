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

public class UserPointHistory {
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String description;
    private Long value;
    private Long result;

    @Builder
    public UserPointHistory(Date createdAt,
                            Date updatedAt,
                            Date deletedAt,
                            Long id,
                            User userId,
                            String description,
                            Long value,
                            Long result) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.value = value;
        this.result = result;
    }
}
