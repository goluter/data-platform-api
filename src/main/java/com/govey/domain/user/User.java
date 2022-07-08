package com.govey.domain.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_entity")
public class User implements UserDetails {
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

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection < GrantedAuthority > collectors = new ArrayList<>();
//        collectors.add(() -> "granting auth");
//        return collectors;
//    }

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}