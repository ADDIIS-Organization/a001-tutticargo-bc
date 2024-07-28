package com.addiis.core.gestionlogistica.persistence.entities.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseActiveEntity;
import com.addiis.core.gestionlogistica.persistence.entities.common.BaseStatusEntity;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderProduct;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends BaseActiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "code")
    private BigInteger code;

    @Column(name = "ean")
    private BigInteger ean;

    @Column(name = "uxc", length = 45)
    private String uxc;

    @Column(name = "cxp", length = 45)
    private String cxp;

    @Column(name = "observation", length = 100)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "product_types_id", referencedColumnName = "id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "units_of_measure_id", referencedColumnName = "id")
    private UnitOfMeasure unitOfMeasure;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_locations_id", referencedColumnName = "id")
    private WarehouseLocation warehouseLocation;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;
}