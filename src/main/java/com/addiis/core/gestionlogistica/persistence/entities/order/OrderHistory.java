package com.addiis.core.gestionlogistica.persistence.entities.order;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="order_history")
public class OrderHistory {

    @EmbeddedId
    private OrderHistoryId id;

    @Column(name = "order_history_date", length = 45)
    private String orderHistoryDate;

    @ManyToOne
    @MapsId("orders_id")
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @MapsId("order_status_id")
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatus orderState;
}
