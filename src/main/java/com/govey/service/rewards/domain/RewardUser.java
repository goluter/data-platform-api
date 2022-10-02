package com.govey.service.rewards.domain;

import com.govey.domain.BaseEntity;
import com.govey.service.users.domain.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Table
@DynamicInsert
@Where(clause = "deleted=false")
public class RewardUser extends BaseEntity {
    @ManyToOne()
    @JoinColumn(nullable = false)
//    @JsonIncludeProperties(value = {"id"})
    private Reward poll;

    @ManyToOne()
    @JoinColumn(nullable = true)
    private User user;
}