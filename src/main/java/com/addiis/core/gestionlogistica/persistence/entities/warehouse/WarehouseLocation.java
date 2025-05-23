package com.addiis.core.gestionlogistica.persistence.entities.warehouse;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
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

    @Column(name = "dispo", length = 40)
    private String dispo;

    @Column(name = "code_sap")
    private String codeSap;

    @Column(name = "observation", length = 150)
    private String observation;

    @OneToOne(mappedBy = "warehouseLocation", cascade = CascadeType.ALL)
    private Product product;

    @OneToMany(mappedBy = "warehouseLocation")
    private Set<ReceptionScannedProduct> receptionScannedProducts;
}