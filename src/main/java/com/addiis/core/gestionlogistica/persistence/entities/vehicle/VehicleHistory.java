package com.addiis.core.gestionlogistica.persistence.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="vehicles_history")
public class VehicleHistory {

    @EmbeddedId
    private VehicleHistoryId id;

    @ManyToOne
    @MapsId("vehicles_id")
    @JoinColumn(name = "vehicles_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToOne
    @MapsId("vehicle_states_id")
    @JoinColumn(name = "vehicle_states_id", referencedColumnName = "id")
    private VehicleStatus vehicleState;
}
