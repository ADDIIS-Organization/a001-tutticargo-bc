package com.addiis.core.gestionlogistica.persistence.entities.order;

import java.math.BigInteger;

import com.addiis.core.gestionlogistica.persistence.entities.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="order_product")
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
}
