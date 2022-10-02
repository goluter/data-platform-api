package com.govey.service.users.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@EqualsAndHashCode(callSuper = true)
@Where(clause = "deleted=false")
public class UserTimeline extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name="user_id", nullable = false)
    @JsonIncludeProperties(value = {"id"})
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private UserTimelineType type;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String link;
}