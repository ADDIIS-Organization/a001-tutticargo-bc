package com.addiis.core.gestionlogistica.persistence.entities.order;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.OffsetDateTime;

import java.time.ZoneOffset;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "detra")
    private BigInteger detra;

    @Column(name = "order_number")
    private BigInteger orderNumber;

    @ManyToOne
    @JoinColumn(name = "stores_id", referencedColumnName = "id")
    @JsonManagedReference
    private Store store;

    @OneToMany(mappedBy = "order")
    private Set<OrderHistory> orderHistories;

    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> ordersProducts;

    // @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    // @JsonManagedReference
    // private Set<OrderPallet> ordersPallets;

    @CreationTimestamp
    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;

    @PrePersist
    public void prePersist() {
        date = Timestamp.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant());
    }
}
