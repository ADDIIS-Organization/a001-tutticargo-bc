package com.addiis.core.gestionlogistica.persistence.entities.warehouse;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseStatusEntity;
import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="warehouse_locations")
public class WarehouseLocation extends BaseStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "prefix")
    private Integer prefix;

    @Column(name = "code")
    private Integer code;

    @Column(name = "observation", length = 150)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToMany(mappedBy = "warehouseLocation")
    private Set<ReceptionScannedProduct> receptionScannedProducts;
}