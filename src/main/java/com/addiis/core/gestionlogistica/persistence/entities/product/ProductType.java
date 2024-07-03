package com.addiis.core.gestionlogistica.persistence.entities.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseStatusEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product_types")
public class ProductType extends BaseStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "observation", length = 150)
    private String observation;

    @OneToMany(mappedBy = "productType")
    private Set<Product> products;
}