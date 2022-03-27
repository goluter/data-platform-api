package com.govey.domain.blog;

import java.time.LocalDate;

public final class BlogBuilder {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;
    private Long id;
    private Blog parent_id;
    private String subject;
    private String content;
    private Boolean is_comment;
    private Long hit_count;
    private Long good_count;
    private Long no_good_count;

    private BlogBuilder() {
    }

    public static BlogBuilder aBlog() {
        return new BlogBuilder();
    }

    public BlogBuilder withCreated_at(LocalDate created_at) {
        this.created_at = created_at;
        return this;
    }

    public BlogBuilder withUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
        return this;
    }

    public BlogBuilder withDeleted_at(LocalDate deleted_at) {
        this.deleted_at = deleted_at;
        return this;
    }

    public BlogBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public BlogBuilder withParent_id(Blog parent_id) {
        this.parent_id = parent_id;
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

    public BlogBuilder withIs_comment(Boolean is_comment) {
        this.is_comment = is_comment;
        return this;
    }

    public BlogBuilder withHit_count(Long hit_count) {
        this.hit_count = hit_count;
        return this;
    }

    public BlogBuilder withGood_count(Long good_count) {
        this.good_count = good_count;
        return this;
    }

    public BlogBuilder withNo_good_count(Long no_good_count) {
        this.no_good_count = no_good_count;
        return this;
    }

    public Blog build() {
        Blog blog = new Blog();
        blog.setCreated_at(created_at);
        blog.setUpdated_at(updated_at);
        blog.setDeleted_at(deleted_at);
        blog.setId(id);
        blog.setParent_id(parent_id);
        blog.setSubject(subject);
        blog.setContent(content);
        blog.setIs_comment(is_comment);
        blog.setHit_count(hit_count);
        blog.setGood_count(good_count);
        blog.setNo_good_count(no_good_count);
        return blog;
    }
}
