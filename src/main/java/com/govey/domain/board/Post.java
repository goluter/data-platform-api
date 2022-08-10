package com.govey.domain.board;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import com.govey.domain.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@DynamicInsert
@Where(clause = "deleted=false")
public class Post extends BaseEntity {
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIncludeProperties(value = {"id"})
    private User user;

    @Column
    private String author;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column()
    private String category;

    @Column
    private Boolean isNotice;

    @Column
    @ColumnDefault("0")
    private Boolean hasAttachment;

    @Column
    @ColumnDefault("0")
    private Integer hits;

    @Column
    @ColumnDefault("0")
    private Integer reportings;

    @Column
    private Integer comments;
}