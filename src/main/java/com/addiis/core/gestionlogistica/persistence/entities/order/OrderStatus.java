package com.addiis.core.gestionlogistica.persistence.entities.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status", length = 45)
    private String status;

    @OneToMany(mappedBy = "orderState")
    private Set<OrderHistory> orderHistories;
}
