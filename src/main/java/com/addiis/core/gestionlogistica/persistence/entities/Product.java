package com.addiis.core.gestionlogistica.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * Entity representing the persistence of master data for products.
 * This entity stores the code and description of a product.
 * The code is a unique identifier for the product, while the description
 * provides additional information.
 * 
 * @author Nicolás Picón Jaimes.
 * @version 1.0.0
 * @since 2024-05-04
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products", indexes = { @Index(columnList = "code") })
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 60)
    @Column(length = 60)
    private String code;

    @Column(name = "ean")
    private int ean;

    @Size(max = 100)
    @Column(length = 100)
    private String description;

    // @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    // private ProductLocation productLocation;
}
