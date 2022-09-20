package com.govey.service.surveys.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
public class PollAnswer extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name="poll_id", nullable = false)
    @JsonIncludeProperties(value = {"id"})
    private Poll poll;

    @ManyToOne()
    @JoinColumn(name="poll_item_id", nullable = false)
    @JsonIncludeProperties(value = {"id"})
    private PollItem pollItem;

    @ManyToOne()
    @JoinColumn(name="user_id", nullable = false)
//    @JsonIncludeProperties(value = {"id"})
    private User user;
}