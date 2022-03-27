package com.govey.domain.survey;

import com.govey.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Survey {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name="id")
    private User author_id;

    private String banner_image_url;
    private String video_url;

    private String status;
    private String homepage;
    private String phone_num;

    private Long hit_count;
    private Long good_count;
    private Long no_good_count;
    private Long point;
    private String target;

    @Column(nullable = true)
    private LocalDate reward_at;
    private LocalDate start_at;
    private LocalDate end_at;
}
