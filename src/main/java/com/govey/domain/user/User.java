package com.govey.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_entity")
public class User {
    @Column(name = "created_at")
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

    private String type;

    @Column(name = "social_login_type")
    private String socialLoginType;
    @Column(name = "social_login_id")
    private String socialLoginId;
    private String account;
    private String password;

    private String name;
    @Column(name = "nick_name")
    private String nickName;

    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private LocalDate birthday;
    private String gender;

    private Long point;
    private Long level;

    @ManyToOne
    @JoinColumn(name = "main_title")
    private Title mainTitle;

    @Column(name = "last_login")
    private LocalDate lastLogin;
    @Column(name = "login_ip")
    private String loginIp;
}