package com.govey.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE USER SET deleted=true where id=?")
@Where(clause = "deleted=false")
public class BaseEntity {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", nullable = false, updatable = false)
//    @CustomDate
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime deletedAt;

    @Column()
    private Boolean deleted = Boolean.FALSE;

    @PrePersist
    public void add() {
        this.id = UUID.randomUUID();
    }

    @PreRemove
    public void deleteUser(){
        this.deleted = true;
    }
}
