package com.addiis.core.gestionlogistica.persistence.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseAuditEntity;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Dispatch;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.DispatchHistory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="vehicles")
public class Vehicle extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "plate", length = 45)
    private String plate;

    @Column(name = "capacity", length = 45)
    private String capacity;

    @Column(name = "observation", length = 150)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "vehicles_types_id", referencedColumnName = "id")
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle")
    private Set<Dispatch> dispatches;

    @OneToMany(mappedBy = "vehicle")
    private Set<VehicleHistory> vehicleHistories;

    @OneToMany(mappedBy = "vehicle")
    private Set<VehicleDriver> vehicleDrivers;
}
