package com.govey.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String type;

    @Column(name = "social_login_type")
    private String socialLoginType;

    @Column(name = "social_login_id")
    private String socialLoginId;

    @Column(name = "account", length = 50, unique = true)
    private String account;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "nickname", length = 50)
    private String nickname;

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

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "deleted_at", insertable = false)
    private Date deletedAt;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public String getUsername() {
        return this.username;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

