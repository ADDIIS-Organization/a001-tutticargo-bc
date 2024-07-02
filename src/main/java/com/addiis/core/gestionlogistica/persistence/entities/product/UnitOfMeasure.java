package com.addiis.core.gestionlogistica.persistence.entities.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="units_of_measure")
public class UnitOfMeasure extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "code")
    private Integer code;

    @Column(name = "observation", length = 150)
    private String observation;

    @OneToMany(mappedBy = "unitOfMeasure")
    private Set<Product> products;
}
