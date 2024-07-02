package com.addiis.core.gestionlogistica.persistence.entities.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class BaseEntity extends BaseAuditEntity {
    @Column(name = "status")
    @JsonIgnore
    private int status = 1;
}
