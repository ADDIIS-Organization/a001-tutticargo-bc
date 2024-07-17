package com.addiis.core.gestionlogistica.persistence.entities.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Size;
import jakarta.persistence.MappedSuperclass; // This annotation is used to specify that the class is an entity superclass and that it should not be mapped to a database table.


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Base Audit entity that contains the common fields for all entities.
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
public class BaseAuditEntity {
    
    @Size(max = 60)
    @Column(length = 60, name = "created_by")
    @JsonIgnore
    private String createdBy;

    @Column(name = "created_at")
    @JsonIgnore
    private Date createdAt;

    @Size(max = 60)
    @Column(length = 60, name = "updated_by")
    @JsonIgnore
    private String updatedBy;

    @Column(name = "updated_at")
    @JsonIgnore
    private Date updatedAt;

    @JsonIgnore
    @PrePersist // This annotation is used to specify a callback method that is called before the entity manager persist operation is actually executed or cascaded.
    protected void onCreate() { // This method is called before the entity is persisted to the database in order to set the createdAt and updatedAt fields.
        createdAt = new Date();
        updatedAt = new Date();
    }

    @JsonIgnore
    @PreUpdate // This annotation is used to specify a callback method that is called before the database update operation is actually executed.
    protected void onUpdate() {// This method is called before the entity is updated in the database in order to update the updatedAt field.
        updatedAt = new Date();
    }
}
