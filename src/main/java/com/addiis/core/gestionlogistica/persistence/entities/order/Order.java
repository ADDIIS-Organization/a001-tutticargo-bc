package com.addiis.core.gestionlogistica.persistence.entities.order;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Timestamp date;
    
    @Column(name = "detra")
    private BigInteger detra;

    @Column(name = "order_number")
    private BigInteger orderNumber;

    @ManyToOne
    @JoinColumn(name = "stores_id", referencedColumnName = "id")
    private Store store;

    @OneToMany(mappedBy = "order")
    private Set<OrderHistory> orderHistories;

    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> ordersProducts;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderPallet> ordersPallets;
}
