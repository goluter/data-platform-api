package com.govey.domain.user;

import java.time.LocalDate;

public final class UserBuilder {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;
    private Long id;
    private String type;
    private String social_login_type;
    private String social_login_id;
    private final String account;
    private final String password;
    private String name;
    private String nick_name;
    private String email;
    private String phone_number;
    private LocalDate birthday;
    private String gender;
    private Long point;
    private Long level;
    private Title main_title;
    private LocalDate last_login;
    private String login_ip;

    private UserBuilder(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public static UserBuilder anUser(String account, String password) {
        return new UserBuilder(account, password);
    }

    public UserBuilder withCreated_at(LocalDate created_at) {
        this.created_at = created_at;
        return this;
    }

    public UserBuilder withUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
        return this;
    }

    public UserBuilder withDeleted_at(LocalDate deleted_at) {
        this.deleted_at = deleted_at;
        return this;
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public UserBuilder withSocial_login_type(String social_login_type) {
        this.social_login_type = social_login_type;
        return this;
    }

    public UserBuilder withSocial_login_id(String social_login_id) {
        this.social_login_id = social_login_id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withNick_name(String nick_name) {
        this.nick_name = nick_name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPhone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public UserBuilder withBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public UserBuilder withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserBuilder withPoint(Long point) {
        this.point = point;
        return this;
    }

    public UserBuilder withLevel(Long level) {
        this.level = level;
        return this;
    }

    public UserBuilder withMain_title(Title main_title) {
        this.main_title = main_title;
        return this;
    }

    public UserBuilder withLast_login(LocalDate last_login) {
        this.last_login = last_login;
        return this;
    }

    public UserBuilder withLogin_ip(String login_ip) {
        this.login_ip = login_ip;
        return this;
    }

    public User build() {
        User user = new User();
        user.setCreated_at(created_at);
        user.setUpdated_at(updated_at);
        user.setDeleted_at(deleted_at);
        user.setId(id);
        user.setType(type);
        user.setSocial_login_type(social_login_type);
        user.setSocial_login_id(social_login_id);
        user.setAccount(account);
        user.setPassword(password);
        user.setName(name);
        user.setNick_name(nick_name);
        user.setEmail(email);
        user.setPhone_number(phone_number);
        user.setBirthday(birthday);
        user.setGender(gender);
        user.setPoint(point);
        user.setLevel(level);
        user.setMainTitle(main_title);
        user.setLast_login(last_login);
        user.setLogin_ip(login_ip);
        return user;
    }
}
