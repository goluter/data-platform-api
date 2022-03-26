package com.govey.domain.survey;

import com.govey.domain.user.User;

import java.time.LocalDate;

public final class SurveyBuilder {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;
    private Long id;
    private final String title;
    private final User author_id;
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
    private LocalDate reward_at;
    private LocalDate start_at;
    private LocalDate end_at;

    private SurveyBuilder(String title, User author_id) {
        this.title = title;
        this.author_id = author_id;
    }

    public static SurveyBuilder aSurvey(String title, User author_id) {
        return new SurveyBuilder(title, author_id);
    }

    public SurveyBuilder withCreated_at(LocalDate created_at) {
        this.created_at = created_at;
        return this;
    }

    public SurveyBuilder withUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
        return this;
    }

    public SurveyBuilder withDeleted_at(LocalDate deleted_at) {
        this.deleted_at = deleted_at;
        return this;
    }

    public SurveyBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SurveyBuilder withBanner_image_url(String banner_image_url) {
        this.banner_image_url = banner_image_url;
        return this;
    }

    public SurveyBuilder withVideo_url(String video_url) {
        this.video_url = video_url;
        return this;
    }

    public SurveyBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public SurveyBuilder withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public SurveyBuilder withPhone_num(String phone_num) {
        this.phone_num = phone_num;
        return this;
    }

    public SurveyBuilder withHit_count(Long hit_count) {
        this.hit_count = hit_count;
        return this;
    }

    public SurveyBuilder withGood_count(Long good_count) {
        this.good_count = good_count;
        return this;
    }

    public SurveyBuilder withNo_good_count(Long no_good_count) {
        this.no_good_count = no_good_count;
        return this;
    }

    public SurveyBuilder withPoint(Long point) {
        this.point = point;
        return this;
    }

    public SurveyBuilder withTarget(String target) {
        this.target = target;
        return this;
    }

    public SurveyBuilder withReward_at(LocalDate reward_at) {
        this.reward_at = reward_at;
        return this;
    }

    public SurveyBuilder withStart_at(LocalDate start_at) {
        this.start_at = start_at;
        return this;
    }

    public SurveyBuilder withEnd_at(LocalDate end_at) {
        this.end_at = end_at;
        return this;
    }

    public Survey build() {
        Survey survey = new Survey();
        survey.setCreated_at(created_at);
        survey.setUpdated_at(updated_at);
        survey.setDeleted_at(deleted_at);
        survey.setId(id);
        survey.setTitle(title);
        survey.setAuthor_id(author_id);
        survey.setBanner_image_url(banner_image_url);
        survey.setVideo_url(video_url);
        survey.setStatus(status);
        survey.setHomepage(homepage);
        survey.setPhone_num(phone_num);
        survey.setHit_count(hit_count);
        survey.setGood_count(good_count);
        survey.setNo_good_count(no_good_count);
        survey.setPoint(point);
        survey.setTarget(target);
        survey.setReward_at(reward_at);
        survey.setStart_at(start_at);
        survey.setEnd_at(end_at);
        return survey;
    }
}
