package com.govey.service.users.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.govey.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nickname", length = 50, unique = true)
    private String nickname;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime birthday;

    @Column
    private String gender;

    @Column
    private Integer point;

    @Column
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