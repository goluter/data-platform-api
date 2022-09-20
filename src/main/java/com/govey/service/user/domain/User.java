package com.govey.service.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.govey.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "user_entity")
@EqualsAndHashCode(callSuper = true)
@Where(clause = "deleted=false")
public class User extends BaseEntity {
    private UserType type;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nickname", length = 50, unique = true)
    private String nickname;

    private String name;

    private String email;

    private String phoneNumber;

    private LocalDateTime birthday;

    private String gender;

    private Integer point;

    private Integer level;

    @Column(name = "last_login")
    private LocalDate lastLogin;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}