package com.addiis.core.gestionlogistica.persistence.entities.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.entities.route.Route;
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

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "detra")
    private Integer detra;

    @Column(name = "petra")
    private Integer petra;

    @Column(name = "date")
    private Date date;

    @Column(name = "priority", length = 45)
    private String priority;

    @ManyToOne
    @JoinColumn(name = "stores_id", referencedColumnName = "id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "routes_id", referencedColumnName = "id")
    private Route route;

    @OneToMany(mappedBy = "order")
    private Set<OrderHistory> orderHistories;

    @OneToMany(mappedBy = "order")
    private Set<OrderPallet> ordersPallets;

    @ManyToMany
    @JoinTable(
        name = "order_product",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;
}