package com.addiis.core.gestionlogistica.persistence.entities.dispatch;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.addiis.core.gestionlogistica.persistence.entities.order.OrderStore;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Driver;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
// @EqualsAndHashCode
@Entity
@Table(name = "dispatches")
public class Dispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dispatch_number")
    private Integer dispatchNumber;

    @Column(name = "observation", length = 150)
    private String observation;

    @Column(name = "date")
    @Builder.Default
    private LocalDate date = LocalDate.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = true)
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "order_store_id", referencedColumnName = "id")
    @JsonBackReference
    private OrderStore orderStore;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "platform_id", referencedColumnName = "id", nullable = true)
    private Platform platform;

    

    @OneToMany(mappedBy = "dispatch")
    @JsonManagedReference
    private Set<DispatchHistory> dispatchHistories;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

        @Override
    public String toString() {
        return "Dispatch{" +
                "id=" + id +
                ", dispatchNumber=" + dispatchNumber +
                ", observation='" + observation + '\'' +
                ", date=" + date +
                ", driver=" + (driver != null ? driver.getId() : null) +
                ", vehicle=" + (vehicle != null ? vehicle.getId() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dispatch dispatch = (Dispatch) o;

        return id != null ? id.equals(dispatch.id) : dispatch.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
