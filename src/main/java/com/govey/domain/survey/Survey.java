package com.govey.domain.survey;

import com.govey.domain.user.User;
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
public class Survey {
    @Column(name = "createdAt")
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

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User authorId;

    @Column(name="banner_image_url")
    private String bannerUrl;
    @Column(name="video_url")
    private String videoUrl;

    private String status;
    private String homepage;
    @Column(name="phone_num")
    private String phoneNum;

    @Column(name="hit_count")
    private Long hitCount;
    @Column(name="good_count")
    private Long goodCount;
    @Column(name="no_good_count")
    private Long noGoodCount;
    private Long point;
    private String target;

    @Column(name="reward_at")
    private Date rewardAt;
    @Column(name="start_at")
    private Date startAt;
    @Column(name="end_at")
    private Date endAt;
}
