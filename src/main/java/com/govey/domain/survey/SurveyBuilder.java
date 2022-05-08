package com.govey.domain.survey;

import com.govey.domain.user.User;

import java.util.Date;

public final class SurveyBuilder {
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Long id;
    private final String title;
    private final User authorId;
    private String bannerUrl;
    private String videoUrl;
    private String status;
    private String homepage;
    private String phoneNum;
    private Long hitCount;
    private Long goodCount;
    private Long noGoodCount;
    private Long point;
    private String target;
    private Date rewardAt;
    private Date startAt;
    private Date endAt;

    private SurveyBuilder(String title, User authorId) {
        this.title = title;
        this.authorId = authorId;
    }

    public static SurveyBuilder aSurvey(String title, User authorId) {
        return new SurveyBuilder(title, authorId);
    }

    public SurveyBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SurveyBuilder withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public SurveyBuilder withDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public SurveyBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SurveyBuilder withBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
        return this;
    }

    public SurveyBuilder withVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

    public SurveyBuilder withPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public SurveyBuilder withHitCount(Long hitCount) {
        this.hitCount = hitCount;
        return this;
    }

    public SurveyBuilder withGoodCount(Long goodCount) {
        this.goodCount = goodCount;
        return this;
    }

    public SurveyBuilder withNoGoodCount(Long noGoodCount) {
        this.noGoodCount = noGoodCount;
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

    public SurveyBuilder withRewardAt(Date rewardAt) {
        this.rewardAt = rewardAt;
        return this;
    }

    public SurveyBuilder withStartAt(Date startAt) {
        this.startAt = startAt;
        return this;
    }

    public SurveyBuilder withEndAt(Date endAt) {
        this.endAt = endAt;
        return this;
    }

    public Survey build() {
        Survey survey = new Survey();
        survey.setCreatedAt(createdAt);
        survey.setUpdatedAt(updatedAt);
        survey.setDeletedAt(deletedAt);
        survey.setId(id);
        survey.setTitle(title);
        survey.setAuthorId(authorId);
        survey.setBannerUrl(bannerUrl);
        survey.setVideoUrl(videoUrl);
        survey.setStatus(status);
        survey.setHomepage(homepage);
        survey.setPhoneNum(phoneNum);
        survey.setHitCount(hitCount);
        survey.setGoodCount(goodCount);
        survey.setNoGoodCount(noGoodCount);
        survey.setPoint(point);
        survey.setTarget(target);
        survey.setRewardAt(rewardAt);
        survey.setStartAt(startAt);
        survey.setEndAt(endAt);
        return survey;
    }
}
