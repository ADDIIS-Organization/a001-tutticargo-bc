package com.addiis.core.gestionlogistica.persistence.entities.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.Date;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
// @EqualsAndHashCode
@Entity
@Table(name = "order_pallet")
public class OrderPallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_store_id", referencedColumnName = "id")
    @JsonBackReference
    private OrderStore orderStore;

    @Column(name = "picker_id")
    private Integer pickerId;

    @Column(name = "checker_id")
    private Integer checkerId;

    @Column(name = "date")
    private Date date;

    @Column(name = "dispo_id")
    private String dispoId;

    @Column(name = "big_pallets")
    private Integer bigPallets;

    @Column(name = "little_pallets")
    private Integer littlePallets;

    @Column(name = "total_pallets")
    private Integer totalPallets;

    // hashCode and equals methods
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderPallet that = (OrderPallet) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dispoId, that.dispoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dispoId);
    }
}
