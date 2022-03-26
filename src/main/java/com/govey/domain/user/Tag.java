package com.govey.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String value;

    private String description;
}
