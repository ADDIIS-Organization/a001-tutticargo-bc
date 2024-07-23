package com.addiis.core.gestionlogistica.persistence.entities.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@Table(name="order_pallet")
public class OrderPallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "picker_id")
    private Integer pickerId;
    
    @Column(name = "checker_id")
    private Integer checkerId;
    
    @Column(name = "date")
    private Date date;
    
    @Column(name = "dispo_id")
    private Integer dispoId;
    
    @Column(name = "big_pallets")
    private Integer bigPallets;
    
    @Column(name = "little_pallets")
    private Integer littlePallets;
    
    @Column(name = "total_pallets")
    private Integer totalPallets;
}
