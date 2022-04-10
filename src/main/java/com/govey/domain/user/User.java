package com.govey.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class User {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private String social_login_type;
    private String social_login_id;
    private String account;
    private String password;

    private String name;
    private String nick_name;

    private String email;
    private String phone_number;
    private LocalDate birthday;
    private String gender;

    private Long point;
    private Long level;

    @ManyToOne
    private Title main_title;

    private LocalDate last_login;
    private String login_ip;
}