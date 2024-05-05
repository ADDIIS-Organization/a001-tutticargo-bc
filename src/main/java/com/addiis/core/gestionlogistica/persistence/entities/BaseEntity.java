package com.addiis.core.gestionlogistica.persistence.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Base entity that contains the common fields for all entities.
 * This entity contains the fields that are common to all entities, such as
 * the user who created and last updated the record, as well as the date and
 * time when the record was created and last updated.
 * 
 * @author Nicolás Picón Jaimes.
 * @version 1.0.0
 * @since 2024-05-04
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Size(max = 60)
    @Column(length = 60, name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Size(max = 60)
    @Column(length = 60, name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "status")
    private int status = 1;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
