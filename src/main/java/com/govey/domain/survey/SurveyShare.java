package com.govey.domain.survey;

import com.govey.domain.user.User;
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

public class SurveyShare {
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
    @JoinColumn(name = "survey_id")
    private Survey surveyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String type;
    private String url;

    @Column(name = "open_count")
    private Long openCount;

    @Builder
    public SurveyShare(Date createdAt,
                       Date updatedAt,
                       Date deletedAt,
                       Long id,
                       Survey surveyId,
                       User userId,
                       String type,
                       String url,
                       Long openCount) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.id = id;
        this.surveyId = surveyId;
        this.userId = userId;
        this.type = type;
        this.url = url;
        this.openCount = openCount;
    }
}
