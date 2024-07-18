package com.addiis.core.gestionlogistica.persistence.entities.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="orders_pallets")
public class OrderPallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "pallet_number")
    private Integer palletNumber;

    @Column(name = "total_pallets")
    private Integer totalPallets;

    @Column(name = "picker_id")
    private Integer pickerId;

    @Column(name = "checker_id")
    private Integer checkerId;

    @Column(name = "date")
    private Date date;

    @Column(name = "dispo_id")
    private Integer dispoId;

    @Column(name = "big_palets")
    private Integer bigPallets;

    @Column(name = "little_palets")
    private Integer littlePallets;
}
