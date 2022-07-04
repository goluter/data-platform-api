package com.govey.domain.blog;

import java.util.Date;

public final class BlogBuilder {
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Long id;
    private Blog parentId;
    private String subject;
    private String content;
    private Boolean isComment;
    private Long hitCount;
    private Long goodCount;
    private Long noGoodCount;

    private BlogBuilder(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public BlogBuilder() {

    }

    public static BlogBuilder aBlog(String subject, String content) {
        return new BlogBuilder(subject, content);
    }

    public BlogBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public BlogBuilder withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public BlogBuilder withDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public BlogBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public BlogBuilder withParentId(Blog parentId) {
        this.parentId = parentId;
        return this;
    }

    public BlogBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public BlogBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public BlogBuilder withIsComment(Boolean isComment) {
        this.isComment = isComment;
        return this;
    }

    public BlogBuilder withHitCount(Long hitCount) {
        this.hitCount = hitCount;
        return this;
    }

    public BlogBuilder withGoodCount(Long goodCount) {
        this.goodCount = goodCount;
        return this;
    }

    public BlogBuilder withNoGoodCount(Long noGoodCount) {
        this.noGoodCount = noGoodCount;
        return this;
    }

    public Blog build() {
        Blog blog = new Blog();
        blog.setCreatedAt(createdAt);
        blog.setUpdatedAt(updatedAt);
        blog.setDeletedAt(deletedAt);
        blog.setId(id);
        blog.setParentId(parentId);
        blog.setSubject(subject);
        blog.setContent(content);
        blog.setIsComment(isComment);
        blog.setHitCount(hitCount);
        blog.setGoodCount(goodCount);
        blog.setNoGoodCount(noGoodCount);
        return blog;
    }
}
