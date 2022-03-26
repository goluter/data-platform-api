package com.govey.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class Level {
    @Id
    @GeneratedValue
    private Long id;

    private Long value;
    private String name;
    private String description;
}
