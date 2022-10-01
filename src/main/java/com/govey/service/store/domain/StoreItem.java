package com.govey.service.store.domain;

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
public class StoreItem extends BaseEntity {
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String notice;

    @Column
    private Integer price;

    @Column
    private String imageUrl;

    @Column
    private Integer stockCount;

    @Column
    private StoreItemStatus status;
}