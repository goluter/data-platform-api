package com.govey.service.rewards.domain;

import com.govey.domain.BaseEntity;
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
@Table
@DynamicInsert
@Where(clause = "deleted=false")
public class Reward extends BaseEntity {
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String requirements;

    @Column
    private Integer value;

    @Column
    private String imageUrl;

    @Column
    private Integer count;
}