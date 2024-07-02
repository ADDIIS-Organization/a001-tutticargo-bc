package com.addiis.core.gestionlogistica.persistence.entities.product;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseEntity;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * Entity representing the persistence of the relationship between products and warehouse locations.
 * This entity stores the quantity of a product stored at a specific location within a warehouse.
 * The quantity is stored as a floating point number to allow for partial quantities.
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
@Table(name="product_warehouse_locations")
public class ProductLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Double quantity;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "warehouse_location_id", referencedColumnName = "id")
    private WarehouseLocation warehouseLocation;
}
