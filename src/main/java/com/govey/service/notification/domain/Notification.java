package com.govey.service.notification.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import com.govey.service.user.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@Where(clause = "deleted=false")
public class Notification extends BaseEntity {
    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne()
    @JoinColumn
    @JsonIncludeProperties(value = {"id"})
    private User user;

    @Column(name = "isSand")
    @ColumnDefault("false")
    private Boolean isSand;

    @Column(name = "isRead")
    @ColumnDefault("false")
    private Boolean isRead;
}