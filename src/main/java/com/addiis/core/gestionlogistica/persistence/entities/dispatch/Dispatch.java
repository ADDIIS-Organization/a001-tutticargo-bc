package com.addiis.core.gestionlogistica.persistence.entities.dispatch;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.addiis.core.gestionlogistica.persistence.entities.order.OrderStore;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Driver;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonBackReference;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
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
    // @JoinColumn(name = "order_store_id", referencedColumnName = "id")
    @JsonBackReference
    private OrderStore orderStore;
    
    // @OneToMany(mappedBy = "dispatch")
    // @JsonManagedReference
    // private Set<DispatchHistory> dispatchHistories;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
}
