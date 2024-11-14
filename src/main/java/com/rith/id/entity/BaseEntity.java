package com.rith.id.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {


    @Column(name = "created_at", updatable = false, nullable = false)
    protected Instant createdAt;


    @Column(name = "modified_at")
    protected Instant modifiedAt;

    @Column
    @Version
    protected int version;

    @Column(name = "created_by")
    protected UUID createdBy;
    @Column(name = "modified_by")
    protected UUID modifiedBy;
}
