package com.govey.service.users.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import com.govey.service.rewards.domain.Reward;
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
public class UserReward extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(nullable = false)
//    @JsonIncludeProperties(value = {"id"})
    private Reward reward;

    private String type;

    private String category;
}