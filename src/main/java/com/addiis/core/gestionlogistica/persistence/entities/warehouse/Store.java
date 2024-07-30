package com.addiis.core.gestionlogistica.persistence.entities.warehouse;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseStatusEntity;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Channel;
import com.addiis.core.gestionlogistica.persistence.entities.order.Order;
import com.addiis.core.gestionlogistica.persistence.entities.route.Route;
import com.fasterxml.jackson.annotation.JsonIgnore;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stores")
public class Store extends BaseStatusEntity {

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
    private String ruc;

    // @ManyToOne
    // @JoinColumn(name = "zones_id", referencedColumnName = "id")
    // private Zone zone;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    // Remove infinite recursion
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private Set<Order> orders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "channel_id", referencedColumnName = "id" , nullable = true)
    private Channel channel;
}