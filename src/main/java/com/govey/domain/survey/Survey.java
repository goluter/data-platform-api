package com.govey.domain.survey;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Survey {
    @Id
    @SequenceGenerator(
            name = "survey_sequence",
            sequenceName = "survey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;
    private String author;
    private LocalDate cr_date;
    private LocalDate exp_date;
    private String target;
    private Integer res_num;
    private Integer hit_count;

    @Builder
    public Survey(String title,
                  String author,
                  LocalDate cr_date,
                  LocalDate exp_date,
                  String target,
                  Integer res_num,
                  Integer hit_count) {
        this.title = title;
        this.author = author;
        this.cr_date = cr_date;
        this.exp_date = exp_date;
        this.target = target;
        this.res_num = res_num;
        this.hit_count = hit_count;
    }

//    @Override
//    public String toString() {
//        return "Survey{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", author='" + author + '\'' +
//                ", cr_date=" + cr_date +
//                ", exp_date=" + exp_date +
//                ", target='" + target + '\'' +
//                ", res_num=" + res_num +
//                ", hit_count=" + hit_count +
//                '}';
//    }
}
