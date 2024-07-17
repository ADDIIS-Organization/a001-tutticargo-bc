package com.addiis.core.gestionlogistica.persistence.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="vehicles_drivers")
public class VehicleDriver {

    @EmbeddedId
    private VehicleDriverId id;

    @Column(name = "assignment_date")
    private Date assignmentDate;

    @Column(name = "deallocation_date")
    private Date deallocationDate;

    @ManyToOne
    @MapsId("drivers_id")
    @JoinColumn(name = "drivers_id", referencedColumnName = "id")
    private Driver driver;

    @ManyToOne
    @MapsId("vehicles_id")
    @JoinColumn(name = "vehicles_id", referencedColumnName = "id")
    private Vehicle vehicle;
}
