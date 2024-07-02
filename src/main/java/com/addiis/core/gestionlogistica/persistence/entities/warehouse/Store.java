package com.addiis.core.gestionlogistica.persistence.entities.warehouse;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseEntity;
import com.addiis.core.gestionlogistica.persistence.entities.order.Order;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="stores")
public class Store extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "code")
    private Integer code;

    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "city", length = 45)
    private String city;

    @Column(name = "observation", length = 150)
    private String observation;

    @Column(name = "priority", length = 45)
    private String priority;

    @Column(name = "ruc")
    private Integer ruc;

    @ManyToOne
    @JoinColumn(name = "zones_id", referencedColumnName = "id")
    private Zone zone;

    @OneToMany(mappedBy = "store")
    private Set<Order> orders;
}
