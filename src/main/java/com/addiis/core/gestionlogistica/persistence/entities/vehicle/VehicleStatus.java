package com.addiis.core.gestionlogistica.persistence.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="vehicle_status")
public class VehicleStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status", length = 45)
    private String status;

    @OneToMany(mappedBy = "vehicleState")
    private Set<VehicleHistory> vehicleHistories;
}
