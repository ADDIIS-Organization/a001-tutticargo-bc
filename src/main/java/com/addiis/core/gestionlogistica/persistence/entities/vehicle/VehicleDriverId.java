package com.addiis.core.gestionlogistica.persistence.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class VehicleDriverId implements Serializable {

    @Column(name = "drivers_id")
    private Long driversId;

    @Column(name = "vehicles_id")
    private Long vehiclesId;
}
