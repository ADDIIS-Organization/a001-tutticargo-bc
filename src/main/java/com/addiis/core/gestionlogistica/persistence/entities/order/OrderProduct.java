package com.addiis.core.gestionlogistica.persistence.entities.order;

import java.math.BigInteger;

import com.addiis.core.gestionlogistica.persistence.entities.product.Product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;

import java.time.ZoneOffset;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "order_product")
public class OrderProduct {
    @EmbeddedId
    private OrderProductId id;

    @Column(name = "quantity")
    private BigInteger quantity;

    @ManyToOne
    @MapsId("orders_id")
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @MapsId("products_id")
    @JoinColumn(name = "products_id", referencedColumnName = "id")
    private Product product;

    @CreationTimestamp
    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;

    @PrePersist
    public void prePersist() {
        date = Timestamp.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant());
    }
}
