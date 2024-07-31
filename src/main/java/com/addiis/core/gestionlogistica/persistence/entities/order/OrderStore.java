package com.addiis.core.gestionlogistica.persistence.entities.order;

import org.hibernate.annotations.CreationTimestamp;

import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;

import jakarta.persistence.*;

import lombok.*;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "order_store")
public class OrderStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToMany(mappedBy = "orderStore")
    private Set<OrderPallet> orderPallets;

    @CreationTimestamp
    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;

    @Column(name = "big_pallets")
    private Integer bigPallets;

    @Column(name = "little_pallets")
    private Integer littlePallets;

    @Column(name = "total_pallets")
    private Integer totalPallets;

    @PrePersist
    public void prePersist() {
        date = Timestamp.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant());
    }

}
