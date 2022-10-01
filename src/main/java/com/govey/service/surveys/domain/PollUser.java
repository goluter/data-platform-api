package com.govey.service.surveys.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.govey.domain.BaseEntity;
import com.govey.service.users.domain.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

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
public class PollUser extends BaseEntity {
    @ManyToOne()
    @JoinColumn(nullable = false)
//    @JsonIncludeProperties(value = {"id"})
    private Poll poll;

    @ManyToOne()
    @JoinColumn(nullable = true)
    private User user;

    @Column(nullable = false)
    private String mainFeature;

//    @ElementCollection
//    @CollectionTable(name = "poll_feature", joinColumns = @JoinColumn(name = "id"))
    @Column(nullable = false)
    private String features;

    @Column(nullable = false)
    private String value;
}