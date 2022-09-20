package com.govey.service.posts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.govey.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "file_entity")
@Table
@DynamicInsert
@Where(clause = "deleted=false")
public class FileEntity extends BaseEntity {
    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String filePath;

    @Column
    private Integer downloadCount;

    @Column
    private Long fileSize;

    // 스토리지 종류
    @Column
    private String storage;

    // 메타데이터
    @Column
    private String metadata;

    @Column
    private String type;

    @Column
    private Date uploadedAt;
}