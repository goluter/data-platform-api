package com.govey.domain.user;

import java.time.LocalDate;
import java.util.Date;

public final class UserBuilder {
    private final String account;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Long id;
    private String type;
    private String socialLoginType;
    private String socialLoginId;
    private String password;
    private String name;
    private String nickName;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private String gender;
    private Long point;
    private Long level;
    private Title mainTitle;
    private LocalDate lastLogin;
    private String loginIp;

    private UserBuilder(String account) {
        this.account = account;
    }

    public static UserBuilder anUser(String account) {
        return new UserBuilder(account);
    }

    public UserBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserBuilder withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public UserBuilder withDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
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

    public UserBuilder withSocialLoginType(String socialLoginType) {
        this.socialLoginType = socialLoginType;
        return this;
    }

    public UserBuilder withSocialLoginId(String socialLoginId) {
        this.socialLoginId = socialLoginId;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public UserBuilder withMainTitle(Title mainTitle) {
        this.mainTitle = mainTitle;
        return this;
    }

    public UserBuilder withLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public UserBuilder withLoginIp(String loginIp) {
        this.loginIp = loginIp;
        return this;
    }

    public User build() {
        User user = new User();
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        user.setDeletedAt(deletedAt);
        user.setId(id);
        user.setType(type);
        user.setSocialLoginType(socialLoginType);
        user.setSocialLoginId(socialLoginId);
        user.setAccount(account);
        user.setPassword(password);
        user.setName(name);
        user.setNickName(nickName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setBirthday(birthday);
        user.setGender(gender);
        user.setPoint(point);
        user.setLevel(level);
        user.setMainTitle(mainTitle);
        user.setLastLogin(lastLogin);
        user.setLoginIp(loginIp);
        return user;
    }
}
