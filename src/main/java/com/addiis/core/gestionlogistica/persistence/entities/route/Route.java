package com.addiis.core.gestionlogistica.persistence.entities.route;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseAuditEntity;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Dispatch;
import com.addiis.core.gestionlogistica.persistence.entities.order.Order;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="routes")
public class Route extends BaseAuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "route_number")
    private String routeNumber;

    @Column(name = "zones", length = 45)
    private String zones;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "observation", length = 45)
    private String observation;

    @OneToMany(mappedBy = "route")
    private Set<Store> stores;

    @ManyToOne
    @JoinColumn(name = "dispatches_id", referencedColumnName = "id")
    private Dispatch dispatch;

    @OneToMany(mappedBy = "route")
    private Set<Order> orders;
}
