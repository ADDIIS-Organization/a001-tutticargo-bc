package com.addiis.core.gestionlogistica.persistence.entities.order;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class OrderHistoryId implements Serializable {

    @Column(name = "orders_id")
    private Long ordersId;

    @Column(name = "order_status_id")
    private Long orderStatesId;
}
