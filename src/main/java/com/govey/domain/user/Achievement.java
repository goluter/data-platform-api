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
public class Achievement {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private String condition_description;

    private Long parent_id;
    private Long leven;
}
