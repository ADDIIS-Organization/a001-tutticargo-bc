package com.addiis.core.gestionlogistica.persistence.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * Entity representing the persistence of master data for locations within a warehouse.
 * Descriptions added to each column for better understanding and documentation.
 *
 * @author Nicolás Picón Jaimes.
 * @version 1.0.0
 * @since 2024-05-04
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="warehouse_locations", indexes = {@Index(columnList = "code")})
public class WarehouseLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Size(max = 60)
    @Column(length = 60, name = "code")
    private String code;

    @Size(max = 100)
    @Column(length = 100, name = "description")
    private String description;

    @Size(max = 40)
    @Column(length = 40, name = "sector")
    private String sector;

    @Size(max = 40)
    @Column(length = 40, name = "space")
    private String space;
}

