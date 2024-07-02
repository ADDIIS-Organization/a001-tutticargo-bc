package com.addiis.core.gestionlogistica.persistence.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class VehicleHistoryId implements Serializable {

    @Column(name = "vehicles_id")
    private Long vehiclesId;

    @Column(name = "vehicle_states_id")
    private Long vehicleStatesId;
}